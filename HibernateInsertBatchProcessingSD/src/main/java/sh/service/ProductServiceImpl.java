package sh.service;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.dao.ProductRepository;
import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductRepository productRepository;
    
    @Override      
    @Transactional
    public void withBatching(int n) {

        // preparing the products
        ArrayList<Product> productsInBatch = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Product p = new Product(i);
            productsInBatch.add(p);
        }                

        long start = System.nanoTime();

        // persist via batching
        productRepository.save(productsInBatch);

        LOG.log(Level.INFO, "INSERTED {0} PRODUCTS IN: {1} MILISECONDS.",
                new Object[]{n, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)});        
    }
}
