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

    /*
     * Equivalent to:

        <bean id="helloWorld" class="org.spring.bean.HelloWorld" />

     */
    @Bean(name = "helloWorld")
    public HelloWorld hello() {
        return new HelloWorld();
    }

}
