package sh.service;

import org.springframework.beans.factory.annotation.Autowired;
import sh.dao.ProductRepository;
import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void persist(Product p) {
        productRepository.save(p);
    }

}
