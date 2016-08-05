package sh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.service.JoinService;

/**
 *
 * @author Anghel Leonard
 */
@Controller
@RequestMapping("/")
public class SHController {

    @Autowired
    private JoinService joinService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "home";
    }

    @RequestMapping(value = "/innerjoin", method = RequestMethod.GET)
    public String innerJoinAction() {
        joinService.innerJoin();

        return "home";
    }

    @RequestMapping(value = "/leftjoin", method = RequestMethod.GET)
    public String leftJoinAction() {
        joinService.leftJoin();

        return "home";
    }

    @RequestMapping(value = "/rightjoin", method = RequestMethod.GET)
    public String rightJoinAction() {
        joinService.rightJoin();

        return "home";
    }

    @RequestMapping(value = "/fulljoin", method = RequestMethod.GET)
    public String fullJoinAction() {
        joinService.fullJoin();

        return "home";
    }

    @RequestMapping(value = "/leftexcludingjoin", method = RequestMethod.GET)
    public String leftExcludingJoinAction() {
        joinService.leftExcludingJoin();

        return "home";
    }

    @RequestMapping(value = "/rightexcludingjoin", method = RequestMethod.GET)
    public String rightExcludingJoinAction() {
        joinService.rightExcludingJoin();

        return "home";
    }

    @RequestMapping(value = "/outerexcludingjoin", method = RequestMethod.GET)
    public String outerExcludingJoinAction() {
        joinService.outerExcludingJoin();

        return "home";
    }

}
