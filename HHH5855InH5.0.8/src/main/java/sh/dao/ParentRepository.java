package sh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.model.Parent;

/**
 *
 * @author Anghel Leonard
 */
public interface ParentRepository extends JpaRepository<Parent, Long> {
    // NOPE
}
