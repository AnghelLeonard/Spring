package sh.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sh.dto.CategoryDTO;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface CategoryService {
    
    public List<CategoryDTO> getByJoiningResultSet();
}
