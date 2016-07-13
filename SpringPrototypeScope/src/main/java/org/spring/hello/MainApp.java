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

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.setName("Alin");
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) context.getBean("helloBean");
        obj2.printHello();
    }

}
