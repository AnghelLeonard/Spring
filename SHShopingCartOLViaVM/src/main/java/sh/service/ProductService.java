package sh.service;

import org.springframework.stereotype.Service;
import sh.model.Inventory;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface ProductService {
    
    public Inventory addInInventory(Inventory inventoryItem);
    public Inventory updateInventory(final Long id);    
}
