package sh.service;

import org.springframework.stereotype.Service;
import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface ProductService {
    
    public void persist(Product p);    
}
