package sh.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.model.Product;
import sh.simpleconfiguration.SHConfiguration;

/**
 *
 * @author Anghel Leonard
 */
@Repository
@Transactional
public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements ProductDAO {

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public void persistWithBatching(int n) {

        int batchSize = SHConfiguration.batchSize();

        for (int i = 0; i < n; i++) {
            Product p = new Product(i);
            super.persist(p);

            // Flush a batch of inserts and release memory
            if (i % batchSize == 0 && i > 0) {
                getEntityManager().flush();
                getEntityManager().clear();
            }
        }
    }
}
