package sh.dao;

import sh.model.Product;

/**
 *
 * @author Anghel Leonard
 */
public interface ProductDAO extends GenericDAO<Product, Long> {
    
   public void persistWithBatching(int n);
}
