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
     * By default, the bean name will be the same as the method name.
     * Equivalent to <bean id="helloBean" class="org.spring.bean.HelloWorld" /> XML configuration.
     */
    @Bean
    public HelloWorld helloBean() {     // use XML bean id as method name
        return new HelloWorld();
    }

    /*
     * By default, the bean name will be the same as the method name.
     * Equivalent to:

        <bean id="helloWorld" class="org.spring.bean.HelloWorld">
            <property name="name" value="world" />
        </bean>

     */
    @Bean
    public HelloWorld helloWorld() {    // use XML bean id as method name
        HelloWorld world = new HelloWorld();
        world.setName("world");
        return world;
    }

}
