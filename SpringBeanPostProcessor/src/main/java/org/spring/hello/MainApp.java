package org.spring.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) context.getBean("helloBean");
        obj2.printHello();

        context.registerShutdownHook();
    }

}
