package swf.sbh.activities;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import swf.sbh.model.Item;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class FindActivityImpl implements FindActivity {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void findRow() {   
        // Our id is of type integer, but we passing a long
        // This will cause 5 retries and will end up with an exception
        LOGGER.debug("-----------------------------------------");
        LOGGER.debug("------------------FIND-------------------");
        LOGGER.debug("-----------------------------------------");
        entityManager.find(Item.class, 1L);        
    }    
}
