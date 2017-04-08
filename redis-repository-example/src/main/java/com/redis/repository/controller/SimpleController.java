package com.redis.repository.controller;

import com.redis.repository.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        helloService.doInsert();

        return "CRUD operations executed on Redis database!";
    }
}
