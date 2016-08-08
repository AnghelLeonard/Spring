package sh.service;

import com.vladmihalcea.concurrent.Retry;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import sh.dao.InventoryRepository;
import sh.model.Inventory;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    @Retry(times = 10, on = ObjectOptimisticLockingFailureException.class)
    public Inventory updateInventory(final Long id) {
        return transactionTemplate.execute(new TransactionCallback<Inventory>() {
            @Override
            public Inventory doInTransaction(TransactionStatus status) {

                Random random = new Random(Thread.currentThread().hashCode());                
                int quantity = random.nextInt(4) + 1;                

                Inventory inventoryItem = inventoryRepository.findOne(1L);
                LOG.info("{} will buy a quantity of {} t-shirts from the inventory containing {} t-shirts", new Object[]{Thread.currentThread().getName(), quantity, inventoryItem.getQuantity()});
                if (inventoryItem.getQuantity() > 0) {
                    if (inventoryItem.getQuantity() - quantity >= 0) {
                        inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
                    } else {
                        LOG.info("Asking " + Thread.currentThread().getName() + " if she/he wants to buy the remaining " + inventoryItem.getQuantity() + " t-shirts since " + quantity + " is not available. Supposing yes ...");
                        quantity = inventoryItem.getQuantity();
                        inventoryItem.setQuantity(0);
                    }
                    LOG.info("Updating inventory: {} bought a quantity of {} t-shirts", new Object[]{Thread.currentThread().getName(), quantity});

                    inventoryRepository.saveAndFlush(inventoryItem);
                } else {
                    LOG.info("Sorry, but for {} there are no t-shirts available ...", Thread.currentThread().getName());
                    return null;
                }
                return inventoryItem;
            }
        });
    }

    @Override
    public Inventory addInInventory(final Inventory inventoryItem) {
        return transactionTemplate.execute(new TransactionCallback<Inventory>() {
            @Override
            public Inventory doInTransaction(TransactionStatus status) {
                return inventoryRepository.saveAndFlush(inventoryItem);
            }
        });
    }
}
