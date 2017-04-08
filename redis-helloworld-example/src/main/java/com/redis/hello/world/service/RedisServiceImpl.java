package com.redis.hello.world.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alin Constantin
 */
@Repository
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> template;

    @Override
    public void sayHello(final String user, final String message) {
        // Insert simple record
        template.opsForValue().set(user, message);
    }

    @Override
    public void insertList() {
        // Insert list record
        template.opsForList().rightPushAll("programming", Arrays.asList("Java", "C", "C++", ".Net"));
    }
}
