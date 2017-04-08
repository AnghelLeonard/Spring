package com.redis.pipeline.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Alin Constantin
 */
@Service
public interface RedisService {

    void doInsertInTransaction();

    void doInsertInPipeline();

    void forceException();
}
