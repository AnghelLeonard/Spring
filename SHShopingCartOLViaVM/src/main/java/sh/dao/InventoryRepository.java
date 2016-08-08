package sh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.model.Inventory;

/**
 *
 * @author Anghel Leonard
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // NOPE
}
