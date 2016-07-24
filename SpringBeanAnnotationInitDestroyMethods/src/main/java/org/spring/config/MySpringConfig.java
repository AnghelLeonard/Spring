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

        <bean class="org.spring.bean.HelloWorld"
		init-method="init" destroy-method="destroy" />

     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public HelloWorld hello() {
        return new HelloWorld();
    }

}
