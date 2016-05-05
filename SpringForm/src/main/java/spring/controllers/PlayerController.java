package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.pojos.Player;

/**
 *
 * @author Anghel Leonard
 */
@Controller
public class PlayerController {

    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public ModelAndView getPlayer() {
        return new ModelAndView("player", "command", new Player());
    }

    @RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
    public String addPlayer(@ModelAttribute("SpringWeb") Player player, ModelMap model) {
        model.addAttribute("name", player.getName());
        model.addAttribute("age", player.getAge());
        model.addAttribute("birthplace", player.getBirthplace());
        model.addAttribute("height", player.getHeight());
        model.addAttribute("weight", player.getWeight());
        model.addAttribute("residence", player.getResidence());

        return "results";
    }
}
