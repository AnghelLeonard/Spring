package sh.service;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.model.TopCategory;
import sh.model.SecondaryCategory;
import sh.model.ThirdCategory;
import sh.pojo.CategoryDTO;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<CategoryDTO> getByJoiningResultSet() {

        TopCategory pc1 = new TopCategory();
        pc1.setName("PRIMARY_CATEGORY_1");
        TopCategory pc2 = new TopCategory();
        pc2.setName("PRIMARY_CATEGORY_2");
        
        SecondaryCategory sc1 = new SecondaryCategory();
        sc1.setName("SECONDARY_CATEGORY_1");
        SecondaryCategory sc2 = new SecondaryCategory();
        sc2.setName("SECONDARY_CATEGORY_2");
        SecondaryCategory sc3 = new SecondaryCategory();
        sc3.setName("SECONDARY_CATEGORY_3");
        
        ThirdCategory tc1 = new ThirdCategory();
        tc1.setName("THIRD_CATEGORY_1");
        ThirdCategory tc2 = new ThirdCategory();
        tc2.setName("THIRD_CATEGORY_2");
        ThirdCategory tc3 = new ThirdCategory();
        tc3.setName("THIRD_CATEGORY_3");
        ThirdCategory tc4 = new ThirdCategory();
        tc4.setName("THIRD_CATEGORY_4");
        ThirdCategory tc5 = new ThirdCategory();
        tc5.setName("THIRD_CATEGORY_5");
        
       
        pc1.addSecondaryCategory(sc1);
        pc1.addSecondaryCategory(sc2);
        pc2.addSecondaryCategory(sc3);
        sc1.addThirdCategory(tc1);
        sc1.addThirdCategory(tc2);
        sc1.addThirdCategory(tc3);
        sc2.addThirdCategory(tc4);
        sc3.addThirdCategory(tc5);

        entityManager.persist(pc1);
        entityManager.persist(pc2);        
        entityManager.flush();
        
        Query query = entityManager.createNativeQuery(
                "SELECT a.name AS namep, b.name AS names, c.name AS namet"
                        + " FROM secondarycategory b"
                        + " INNER JOIN topcategory a ON a.id=b.top_id"
                        + " INNER JOIN thirdcategory c ON b.id=c.secondary_id", "CategoryDTOMapping");
        List<CategoryDTO> result = query.getResultList();
        
        return result;
    }
}
