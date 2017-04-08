package com.redis.repository.repository;

import com.redis.repository.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Alin Constantin
 */
public interface PersonRepository extends CrudRepository<Person, String> {
}
