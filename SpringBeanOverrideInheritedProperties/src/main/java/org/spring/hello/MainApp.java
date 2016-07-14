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

        HelloRomania romania = (HelloRomania) context.getBean("helloRomania");
        System.out.println(romania);

        HelloRomania romania2 = (HelloRomania) context.getBean("helloRomania");
        romania2.printHello();
    }

}
