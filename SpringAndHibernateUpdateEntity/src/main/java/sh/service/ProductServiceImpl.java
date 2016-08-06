package sh.service;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import java.util.List;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void saveProduct(Product p) {
        
        productRepository.save(p);
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product findProductByIdentifier(Long id) {
        
        return productRepository.findOne(id);
    }

}
