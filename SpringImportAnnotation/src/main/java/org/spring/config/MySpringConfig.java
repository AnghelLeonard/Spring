package org.spring.config;

import org.spring.bean.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Constantin Alin
 */
@Configuration  // Marks this class as a source of bean definitions, no need for spring-beans.xml file
@Import(MyEuropeConfig.class)   // Allows for loading @Bean definitions from another configuration class
public class MySpringConfig {

    public MySpringConfig() {
    }

    /*
     * Instantiates, configures and initializes a new object to be managed by the Spring IoC container.
     *
     * Equivalent to <bean class="org.spring.hello.HelloWorld" /> XML configuration.
     */
    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }

}
