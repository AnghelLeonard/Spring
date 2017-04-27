package data.test.samples;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import javax.persistence.EntityManager;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
import org.junit.Before;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SHConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPABeforeAfterTransactionTest extends JPATransactionExecutor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    @FunctionalInterface
    public interface CustomBeforeAfterTransaction<T> extends BeforeAfterTransaction<EntityManager> {

        @Override
        default void beforeTransaction() {
            LOG.info("--------- YEAP! A TRANSACTION WILL START -----------");
        }

        @Override
        default void afterTransaction() {
            LOG.info("---------- DONE! THE TRANSACION IS OVER -----------");
        }
    }

    @Before
    public void init() {
        JPATransactionExecutor.setDatabaseInfo(DataSourceStrategy.MYSQL, true, true, 5, null);
    }

    @Test
    public void test() throws InterruptedException {

        LOG.info("Start 'JPABeforeAfterTransactionTest' test ...");
        executeJPATransaction((CustomBeforeAfterTransaction<EntityManager>) (EntityManager em) -> {
            try {
                Inventory inventory = new Inventory();

                inventory.setName("T-shirts");
                inventory.setQuantity(10);
                
                SQLStatementCountValidator.reset();
                em.persist(inventory);
                assertInsertCount(1);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }, false);
        LOG.info("End 'JPABeforeAfterTransactionTest' test ...");
    }
}
