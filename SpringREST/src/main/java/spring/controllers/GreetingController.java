package spring.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anghel Leonard
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";    

    @RequestMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new ResponseEntity<>(String.format(template, name), HttpStatus.OK);
    }
}
