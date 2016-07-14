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

        /*
         * Throws org.springframework.beans.factory.BeanIsAbstractException:
         * Error creating bean with name 'helloEurope': Bean definition is abstract
         *
         * Actually, this class and its source code does not need to exist
         */
        // HelloEurope europe = (HelloEurope) context.getBean("helloEurope");
        // System.out.println(europe);
		
        HelloRomania romania = (HelloRomania) context.getBean("helloRomania");
        System.out.println(romania);    // the continent property value will be inherited from abstract class

        HelloRomania romania2 = (HelloRomania) context.getBean("helloRomania");
        romania2.printHello();          // the continent property value will be inherited from abstract class
    }

}
