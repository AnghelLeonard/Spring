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
    public String storeAction() {        
        productService.storeData();
        return "home";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateAction() {        
        productService.updateData();
        return "home";
    }
}
