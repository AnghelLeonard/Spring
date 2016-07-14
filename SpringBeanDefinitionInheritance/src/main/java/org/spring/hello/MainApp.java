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

        HelloEurope europe = (HelloEurope) context.getBean("helloEurope");
        System.out.println(europe);

        HelloEurope europe2 = (HelloEurope) context.getBean("helloEurope");
        europe2.printHello();

        HelloRomania romania = (HelloRomania) context.getBean("helloRomania");
        System.out.println(romania);    // the continent property value will be inherited

        HelloRomania romania2 = (HelloRomania) context.getBean("helloRomania");
        romania2.printHello();          // the continent property value will be inherited
    }

}
