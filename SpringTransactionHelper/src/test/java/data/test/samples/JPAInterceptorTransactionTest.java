package data.test.samples;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
import javax.persistence.EntityManager;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import sh.model.Inventory;
import data.utils.DataSourceEnum.DataSourceStrategy;
import data.config.optimistic.locking.vm.SHConfigurationTest;
import data.jpa.executor.JPATransactionExecutor;
import data.jpa.executor.JPATransactionExecutor.BeforeAfterTransaction;
import java.util.concurrent.atomic.AtomicBoolean;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SHConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPAInterceptorTransactionTest extends JPATransactionExecutor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    private final AtomicBoolean applyInterceptor = new AtomicBoolean();

    @Override
    protected Interceptor interceptor() {
        return new EmptyInterceptor() {
            @Override
            public void beforeTransactionCompletion(Transaction tx) {
                if (applyInterceptor.get()) {

                    LOG.info("Custom message for Interceptor#beforeTransactionCompletion() ... ");

                    // for example rollback the transaction
                    // tx.rollback();
                } else {
                    LOG.info("Well, this message is dummy ... ");
                }
            }
        };
    }

    @Before
    public void init() {
        JPATransactionExecutor.setDatabaseInfo(DataSourceStrategy.MYSQL, true, true, 5, interceptor());
    }

    @Test
    public void test() throws InterruptedException {

        LOG.info("Start 'JPAInterceptorTransactionTest' test ...");
        executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_ts) -> {
            try {
                Inventory inventory_ts = new Inventory();

                inventory_ts.setName("T-shirts");
                inventory_ts.setQuantity(10);

                applyInterceptor.set(true); // this will not have any effect 

                SQLStatementCountValidator.reset();
                em_ts.persist(inventory_ts);
                assertInsertCount(1);

                executeSync(() -> {
                    executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_shoes) -> {
                        try {
                            Inventory inventory_shoes = new Inventory();
                            inventory_shoes.setName("Shoes");
                            inventory_shoes.setQuantity(20);

                            applyInterceptor.set(false);

                            SQLStatementCountValidator.reset();
                            em_shoes.persist(inventory_shoes);
                            assertInsertCount(1);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    }, false);
                });

                inventory_ts.setQuantity(5);

                applyInterceptor.set(true); // this will have the effect of calling beforeTransactionCompletion()

                SQLStatementCountValidator.reset();
                em_ts.flush();
                assertUpdateCount(1);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }, false);
        LOG.info("End 'JPAInterceptorTransactionTest' test ...");
    }
}
