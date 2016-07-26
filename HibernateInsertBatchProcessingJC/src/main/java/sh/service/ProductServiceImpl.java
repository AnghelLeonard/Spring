package sh.service;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.model.Product;
import sh.simpleconfiguration.SHConfiguration;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private JpaContext jpaContext;

    @Override
    @Transactional
    public void withBatching(int n, int expected) {        

        EntityManager em = jpaContext.getEntityManagerByManagedType(Product.class);

        SQLStatementCountValidator.reset();

        long start = System.nanoTime();
        int batchSize = SHConfiguration.batchSize();

        // persist via batching        
        for (int i = 0; i < n; i++) {
            Product p = new Product(i);
            em.persist(p);

            // Flush a batch of inserts and release memory
            if (i % batchSize == 0 && i > 0) {
                em.flush();
                em.clear();
            }
        }
        // done

        LOG.log(Level.INFO, "INSERTED {0} PRODUCTS IN: {1} MILISECONDS.",
                new Object[]{n, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)});

        assertInsertCount(expected);
    }
}
