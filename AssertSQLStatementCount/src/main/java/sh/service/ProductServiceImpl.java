package sh.service;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
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
    public void storeProducts() {
        
        Product p1 = new Product();
        p1.setIdproducts(1);
        p1.setName("TV");
        p1.setCode("001");

        Product p2 = new Product();
        p2.setIdproducts(2);
        p2.setName("Microphone");
        p2.setCode("002");

        Product p3 = new Product();
        p3.setIdproducts(3);
        p3.setName("Phone");
        p3.setCode("003");
        
        SQLStatementCountValidator.reset();
        
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        
        assertInsertCount(1);
    }

}
