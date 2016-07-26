package sh.service;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.dao.ProductDAO;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public void withBatching(int n, int expected) {

        SQLStatementCountValidator.reset();
        long start = System.nanoTime();

        // persist via batching    
        productDAO.persistWithBatching(n);

        LOG.log(Level.INFO, "INSERTED {0} PRODUCTS IN: {1} MILISECONDS.",
                new Object[]{n, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)});

        assertInsertCount(expected);
    }
}
