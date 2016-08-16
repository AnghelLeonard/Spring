package sh.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sh.pojo.CategoryDTO;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface ProductService {
    
    public List<CategoryDTO> getByJoiningResultSet();
}
