package sh.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface JoinService {
    
    void innerJoin();
    void leftJoin();
    void rightJoin();
    void fullJoin();
    void leftExcludingJoin();
    void rightExcludingJoin();    
    void outerExcludingJoin();
}
