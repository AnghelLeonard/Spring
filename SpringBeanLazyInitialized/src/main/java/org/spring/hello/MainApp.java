package org.spring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        /*
         * ApplicationContext implementations eagerly create and configure all singleton beans as part of
         * the initialization process
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

        // Output: HelloPortugal constructor invoked..
    }

}
