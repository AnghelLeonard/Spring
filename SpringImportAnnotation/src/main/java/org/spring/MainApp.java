package org.spring;

import org.spring.bean.HelloEurope;
import org.spring.bean.HelloWorld;
import org.spring.config.MySpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);

        HelloWorld world = (HelloWorld) context.getBean(HelloWorld.class);
        world.printHello();

        HelloWorld world2 = (HelloWorld) context.getBean(HelloWorld.class);
        world2.setName("world");
        world2.printHello();

        HelloEurope europe = (HelloEurope) context.getBean(HelloEurope.class);
        europe.printHello();

        HelloEurope europe2 = (HelloEurope) context.getBean(HelloEurope.class);
        europe2.setName("europe");
        europe2.printHello();
    }

}
