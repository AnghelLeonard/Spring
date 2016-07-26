package sh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void persist(Product p) {
        productRepository.save(p);
    }

}
