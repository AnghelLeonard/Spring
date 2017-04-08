package com.redis.hello.world.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Alin Constantin
 */
@Service
public interface RedisService {

    void sayHello(String user, String message);

    void insertList();
}
