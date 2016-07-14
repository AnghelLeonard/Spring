package org.spring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

        Countries obj = (Countries) context.getBean("countries");
        System.out.println(obj);

        Countries obj2 = (Countries) context.getBean("countries");
        System.out.println(obj2);
    }

}
