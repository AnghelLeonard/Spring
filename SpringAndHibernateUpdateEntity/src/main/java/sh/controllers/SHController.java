package sh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    // load the available products from the database
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("products", productService.getProducts());

        return "home";
    }

    // show a form dedicated for creating a new (empty) product (Product instance)
    @RequestMapping(value = "/newproduct", method = RequestMethod.GET)
    public ModelAndView showNewProductForm() {

        return new ModelAndView("newproduct", "command", new Product());
    }

    // show a form dedicated for updating an existing product
    @RequestMapping(value = "/product/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateProductForm(@PathVariable("id") int id, Model model) {

        return new ModelAndView("updateproduct", "command",
                productService.findProductByIdentifier(Long.valueOf(id)));
    }
    
     // save/update the product
    @RequestMapping(value = "/saveorupdateproduct", method = RequestMethod.POST)
    public String saveOrUpdateNewProduct(@ModelAttribute("product") Product product) {

        productService.saveProduct(product);

        return "redirect:/";
    }
}
