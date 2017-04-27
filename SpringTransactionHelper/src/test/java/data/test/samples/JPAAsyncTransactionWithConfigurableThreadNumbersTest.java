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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SHConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPAAsyncTransactionWithConfigurableThreadNumbersTest extends JPATransactionExecutor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    @Before
    public void init() {
        JPATransactionExecutor.setDatabaseInfo(DataSourceStrategy.MYSQL, true, true, 5, null);
    }

    @Test
    public void test() throws InterruptedException {

        LOG.info("Start 'JPAAsyncTransactionWithConfigurableThreadNumbersTest' test ...");
        List<VoidCallable> transactions = Collections.unmodifiableList(Arrays.asList(
                () -> executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em) -> {
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
                }, false),
                () -> executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_shoes) -> {
                    try {
                        Inventory inventory_shoes = new Inventory();
                        inventory_shoes.setName("Shoes");
                        inventory_shoes.setQuantity(20);
                        
                        SQLStatementCountValidator.reset();
                        em_shoes.persist(inventory_shoes);
                        assertInsertCount(1);
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                }, false),
                () -> executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_hats) -> {
                    try {
                        Inventory inventory_hats = new Inventory();
                        inventory_hats.setName("Hats");
                        inventory_hats.setQuantity(50);
                        
                        SQLStatementCountValidator.reset();
                        em_hats.persist(inventory_hats);
                        assertInsertCount(1);
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                }, false),
                () -> executeJPATransaction((BeforeAfterTransaction<EntityManager>) (EntityManager em_gloves) -> {
                    try {
                        Inventory inventory_gloves = new Inventory();
                        inventory_gloves.setName("Gloves");
                        inventory_gloves.setQuantity(150);
                        
                        SQLStatementCountValidator.reset();
                        em_gloves.persist(inventory_gloves);
                        assertInsertCount(1);
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                }, false)
        ));
        
        // second argument is the number of threads to be used
        executeAsync(transactions, 2);
        
        LOG.info("End 'JPAAsyncTransactionWithConfigurableThreadNumbersTest' test ...");
    }
}
