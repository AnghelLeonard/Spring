package com.redis.repository.service;

import com.redis.repository.model.Person;
import com.redis.repository.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alin Constantin
 */
@Repository
public class RedisServiceImpl implements RedisService {

    private static final Logger LOG = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void doInsert() {
        final Person person = new Person();
        person.setId("1");
        person.setFirstname("Alin");
        person.setLastname("Constantin");

        personRepository.save(person);

        LOG.info("SAVED!");

        boolean exists = personRepository.exists(person.getId());
        if (exists) {
            LOG.info("ENTRY EXISTS!");

            final Person p = personRepository.findOne(person.getId());
            LOG.info("ENTRY: " + p);
        }
    }
}
