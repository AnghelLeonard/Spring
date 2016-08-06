package sh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
public interface ProductRepository extends JpaRepository<Product, Long> {    
    // NOPE
}
