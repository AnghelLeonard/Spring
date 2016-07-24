package org.spring;

import org.spring.bean.HelloWorld;
import org.spring.config.MySpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);

        HelloWorld obj = (HelloWorld) context.getBean(HelloWorld.class);
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) context.getBean(HelloWorld.class);
        obj2.setName("world");
        obj2.printHello();

        context.registerShutdownHook();
    }

}
