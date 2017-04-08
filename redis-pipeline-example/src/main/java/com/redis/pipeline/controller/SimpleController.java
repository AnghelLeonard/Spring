package com.redis.pipeline.controller;

import com.redis.pipeline.service.RedisService;
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

        helloService.doInsertInTransaction();

        helloService.doInsertInPipeline();

        helloService.forceException();  // Should be caught by Spring Data

        return "CRUD operations executed on Redis database!";
    }
}
