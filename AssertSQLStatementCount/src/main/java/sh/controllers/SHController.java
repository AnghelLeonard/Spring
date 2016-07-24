package sh.controllers;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.model.Product;
import sh.service.ProductService;

/**
 *
 * @author Anghel Leonard
 */
@Controller
@RequestMapping("/")
public class SHController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "home";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert() {

        Product p1 = new Product();
        p1.setIdproducts(1);
        p1.setName("TV");
        p1.setCode("001");

        Product p2 = new Product();
        p2.setIdproducts(2);
        p2.setName("Microphone");
        p2.setCode("002");

        Product p3 = new Product();
        p3.setIdproducts(3);
        p3.setName("Phone");
        p3.setCode("003");

        SQLStatementCountValidator.reset();
        productService.persist(p1);
        productService.persist(p2);
        productService.persist(p3);                
        assertInsertCount(1);
        return "home";
    }
}
