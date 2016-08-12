package data.test.samples;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
import data.jpa.transaction.executor.JPATransactionExecutor;
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
import javax.persistence.LockModeType;
import org.junit.Before;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SHConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OptimisticForceIncrementCausingOptimisticLockingTest extends JPATransactionExecutor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    @Before
    public void init() {
        JPATransactionExecutor.setDatabaseInfo(DataSourceStrategy.MYSQL, true, true, 5);
    }

    @Test    
    public void test() throws InterruptedException {

        LOG.info("Start 'OptimisticForceIncrementCausingOptimisticLockingTest' test ...");

        executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em) -> {
            try {
                Inventory inventory = new Inventory();

                inventory.setName("T-shirts");
                inventory.setQuantity(10);
                
                em.persist(inventory);               
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });

        executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_ts) -> {
            try {
                SQLStatementCountValidator.reset();
                Inventory inventory_ts = (Inventory) em_ts.createQuery("SELECT p FROM Inventory p WHERE p.name = 'T-shirts'").getSingleResult();
                assertSelectCount(1);

                executeSync(() -> {
                    executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_ts_update) -> {
                        try {                           
                            Inventory find_inventory_ts = em_ts_update.find(Inventory.class, inventory_ts.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                                                        
                            find_inventory_ts.setQuantity(find_inventory_ts.getQuantity());
                            SQLStatementCountValidator.reset();
                            em_ts_update.flush();
                            assertUpdateCount(0);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    });
                });

                inventory_ts.setQuantity(15);
                
                SQLStatementCountValidator.reset();
                em_ts.flush();
                assertUpdateCount(0);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
        LOG.info("End 'OptimisticForceIncrementCausingOptimisticLockingTest' test ...");
    }
}
