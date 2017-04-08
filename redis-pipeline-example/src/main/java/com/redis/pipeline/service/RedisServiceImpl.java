package com.redis.pipeline.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
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
    public void doInsertInTransaction() {
        /* Insert using a transaction
         * Redis will queue up commands from that same connection until it sees an EXEC, at
         * which point Redis will execute the queued commands sequentially without interruption.
         */
        List<Object> result = template.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("key1", "value1");
                operations.opsForValue().set("key2", "value2");
                operations.opsForValue().set("key3", "value3");
                operations.opsForValue().set("key4", "value4");
                return operations.exec();
            }
        });
    }

    @Override
    public void doInsertInPipeline() {
        /* Insert using a pipeline
         * Sends multiple commands to the server without waiting for
         * the replies and then reading the replies in a single step.
         */
        List<Object> result = template.executePipelined((RedisConnection connection) -> {
            for (int i = 0; i < 10; ++i) {
                template.opsForList().rightPush("users", "user_" + i);
            }
            return null;
        });
    }

    @Override
    public void forceException() {
        try {
            // Throws "redis.clients.jedis.exceptions.JedisDataException: ERR no such key"
            template.opsForList().set("empty", 0, new ArrayList());
        } catch (DataAccessException e) {
            LoggerFactory.getLogger(this.getClass()).error("Exception: " + e.getMostSpecificCause().getMessage());
        }
    }
}
