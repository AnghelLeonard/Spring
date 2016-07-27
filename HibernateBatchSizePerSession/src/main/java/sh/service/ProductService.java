package sh.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface ProductService {
    
    public void withBatching(int n, int expected);
}
