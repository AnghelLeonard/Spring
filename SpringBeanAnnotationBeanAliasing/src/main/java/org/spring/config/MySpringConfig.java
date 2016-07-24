package org.spring.config;

import org.spring.bean.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Constantin Alin
 */
@Configuration  // Marks this class as a source of bean definitions, no need for spring-beans.xml file
public class MySpringConfig {

    public MySpringConfig() {
    }

    @Bean(name = {"hello", "world", "helloWorld"})  // Give a single bean multiple names
    public HelloWorld hello() {
        return new HelloWorld();
    }

}
