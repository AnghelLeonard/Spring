package sh.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface ProductService {
    
    public List<Product> getProducts();
    public void saveProduct(Product p); 
    public Product findProductByIdentifier(Long id);
}
