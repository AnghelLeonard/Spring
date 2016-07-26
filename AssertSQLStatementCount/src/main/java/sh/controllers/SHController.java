package sh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public String store() {

        productService.storeProducts();
        
        return "home";
    }
}
