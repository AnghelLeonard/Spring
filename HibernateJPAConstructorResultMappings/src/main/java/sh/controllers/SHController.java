package sh.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.dto.CategoryDTO;
import sh.service.CategoryService;

/**
 *
 * @author Anghel Leonard
 */
@Controller
@RequestMapping("/")
public class SHController {

    @Autowired
    private CategoryService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "home";
    }

    @RequestMapping(value = "/dto", method = RequestMethod.GET)
    public String dtoAction(Model model) {
        
        List<CategoryDTO> dto = 
                productService.getByJoiningResultSet();
        
        model.addAttribute("dto", dto);
        
        return "home";
    }
}
