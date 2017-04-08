package com.redis.hello.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.redis.hello.world.service.RedisService;

/**
 *
 * @author Alin Constantin
 */
@RestController
public class SimpleController {

    @Autowired
    private RedisService helloService;

    @GetMapping("/")
    public String testRedis() {

        helloService.sayHello("Alin", "Hello Redis!");

        helloService.insertList();

        return "CRUD operations executed on Redis database!";
    }
}
