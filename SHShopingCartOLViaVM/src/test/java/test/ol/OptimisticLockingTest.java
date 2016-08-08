package test.ol;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import sh.model.Inventory;
import sh.service.ProductService;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SHConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OptimisticLockingTest {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OptimisticLockingTest.class.getName());

    @Autowired
    private ProductService productService;

    @Test
    public void invetoryRetriesTest() throws InterruptedException {

        final int threadsNumber = 10;
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch startLatch = new CountDownLatch(threadsNumber + 1);
        final CountDownLatch endLatch = new CountDownLatch(threadsNumber + 1);
        final String[] threadNames = new String[]{"Eva", "Jayden", "David", "Anna", "Jake",
            "Adam", "Oscar", "Amelia", "Evie", "Riley"};

        Inventory inventoryItem = new Inventory();
        inventoryItem.setId(1L);
        inventoryItem.setName("T-shirts");
        inventoryItem.setQuantity(8);

        SQLStatementCountValidator.reset();
        productService.addInInventory(inventoryItem);
        assertInsertCount(1);

        for (; atomicInteger.get() < threadsNumber; atomicInteger.incrementAndGet()) {
            final long index = (long) atomicInteger.get() * threadsNumber;
            LOG.info("Scheduling thread index {}", index);
            Thread testThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startLatch.countDown();
                        startLatch.await();
                        productService.updateInventory(1L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        LOG.error("Exception thrown!{}", e);
                    } finally {
                        endLatch.countDown();
                    }
                }
            });
            testThread.setName(threadNames[atomicInteger.get()]);
            testThread.start();
        }
        startLatch.countDown();
        LOG.info("Waiting for threads to be done ...");
        endLatch.countDown();
        endLatch.await();
        LOG.info("Threads are done !");
    }

}
