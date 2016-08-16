package data.jpa.transaction.executor;

import data.source.PostgreSQLDataSource;
import data.source.MySQLDataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.slf4j.LoggerFactory;
import data.utils.DataSourceEnum.DataSourceStrategy;
import data.jpa.emf.HibernateEntityManagerFactory;
import java.sql.Connection;
import org.hibernate.Interceptor;
import org.hibernate.Session;

/**
 *
 * @author Anghel Leonard
 */
public abstract class JPATransactionExecutor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPATransactionExecutor.class.getName());

    private static EntityManagerFactory entityManagerFactory;

    private static ExecutorService asyncExecutorService = null;
    private final ExecutorService syncExecutorService = Executors.newSingleThreadExecutor(new MyThreadFactory());
    private final ScheduledExecutorService syncScheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new MyThreadFactory());
    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void setDatabaseInfo(DataSourceStrategy dss, boolean proxy, boolean hikaricp, int poolsize, Interceptor interceptor) {
        if (dss == DataSourceStrategy.MYSQL) {
            entityManagerFactory = HibernateEntityManagerFactory.getEntityManagerFactory(new MySQLDataSource(), proxy, hikaricp, poolsize, interceptor);
        } else if (dss == DataSourceStrategy.POSTGRESQL) {
            entityManagerFactory = HibernateEntityManagerFactory.getEntityManagerFactory(new PostgreSQLDataSource(), proxy, hikaricp, poolsize, interceptor);
        }
    }

    public class MyThreadFactory implements ThreadFactory {

        int threadNo = 0;

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            threadNo++;
            t.setName("MyExecutor-" + threadNo);

            t.setUncaughtExceptionHandler((Thread t1, Throwable e) -> {
                LoggerFactory.getLogger(t1.getName()).error(e.getMessage(), e);
            });

            return t;
        }
    }
    
    protected Interceptor interceptor() {
        return null;
    }

    @FunctionalInterface
    protected interface VoidCallable extends Callable<Void> {

        void execute();

        @Override
        default Void call() throws Exception {
            execute();
            return null;
        }
    }

    @FunctionalInterface
    protected interface VoidLatchCallable extends Callable<Void> {

        void execute();

        @Override
        default Void call() throws Exception {
            execute();
            JPATransactionExecutor.latch.countDown();
            return null;
        }
    }

    @FunctionalInterface
    public interface BeforeAfterTransaction<T> extends Consumer<EntityManager> {

        default void beforeTransaction() {
            LOG.info("--------- BEFORE TRANSACTION BEGINS -----------");
        }

        default void afterTransaction() {
            LOG.info("---------- AFTER TRANSACTION COMMIT-----------");
        }
    }

    protected void executeSync(VoidCallable callable) throws InterruptedException {
        executeSync(Collections.singleton(callable));
    }

    protected void executeSync(Collection<VoidCallable> callables) throws InterruptedException {

        syncExecutorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new IllegalStateException(e);
                    }
                }).forEach(System.out::println);

        syncExecutorService.shutdown();
    }

    protected void executeScheduledSync(VoidLatchCallable callable, long delayseconds) throws InterruptedException {

        ScheduledFuture<?> scheduleFuture = syncScheduledExecutorService.schedule(callable, delayseconds, TimeUnit.SECONDS);

        long remainingDelay = scheduleFuture.getDelay(TimeUnit.SECONDS);
        LOG.info("The next transaction will run in around " + remainingDelay + " seconds ...");

        latch.await();

        syncScheduledExecutorService.shutdown();

    }

    protected void executeAsync(Collection<VoidCallable> callables, int threadpoolsize) throws InterruptedException {

        asyncExecutorService = Executors.newFixedThreadPool(threadpoolsize, new MyThreadFactory());

        asyncExecutorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new IllegalStateException(e);
                    }
                }).forEach(System.out::println);

        asyncExecutorService.shutdown();
    }

    public void executeJPATransaction(BeforeAfterTransaction<EntityManager> f, boolean evictCache) {
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            Session session = (Session) entityManager.getDelegate();
            session.doWork((Connection connection) -> {
                String result;
                int til = connection.getTransactionIsolation();
                switch (til) {
                    case Connection.TRANSACTION_READ_COMMITTED:
                        result = "TRANSACTION_READ_COMMITTED";
                        break;
                    case Connection.TRANSACTION_READ_UNCOMMITTED:
                        result = "TRANSACTION_READ_UNCOMMITTED";
                        break;
                    case Connection.TRANSACTION_REPEATABLE_READ:
                        result = "TRANSACTION_REPEATABLE_READ";
                        break;
                    case Connection.TRANSACTION_SERIALIZABLE:
                        result = "TRANSACTION_SERIALIZABLE";
                        break;
                    default:
                        result = "TRANSACTION_NONE";
                        break;
                }
                LOG.info("Current transaction isolation level: " + result);
            });

            f.beforeTransaction();
            txn = entityManager.getTransaction();
            txn.begin();

            if (evictCache) {
                session.getSessionFactory().getCache().evictAllRegions();
            }

            f.accept(entityManager);

            txn.commit();
        } catch (RuntimeException e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }
            throw (e);
        } finally {
            f.afterTransaction();
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }       
}
