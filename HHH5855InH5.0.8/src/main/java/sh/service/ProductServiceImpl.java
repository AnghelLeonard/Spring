package sh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.dao.ParentRepository;
import sh.model.Child;
import sh.model.Parent;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ParentRepository parentRepository;

    @Override
    @Transactional
    public void storeData() {
        Parent mike = new Parent();
        mike.setParentname("Mike");
        Child mike_c_1 = new Child();
        mike_c_1.setChildname("Mike child 1");
        Child mike_c_2 = new Child();
        mike_c_2.setChildname("Mike child 2");
        mike.addChild(mike_c_1);
        mike.addChild(mike_c_2);

        parentRepository.save(mike);
    }

    @Override
    @Transactional
    public void updateData() {
        Parent mike = parentRepository.findOne(1L);
        Child mike_c_3 = new Child();
        mike_c_3.setChildname("Mike child 3");
        mike.addChild(mike_c_3);

        parentRepository.save(mike);
    }

}
