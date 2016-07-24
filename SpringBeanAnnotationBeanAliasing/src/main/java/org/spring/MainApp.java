package org.spring;

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

        HelloWorld obj = (HelloWorld) context.getBean("hello");
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) context.getBean("world");
        obj2.setName("world");
        obj2.printHello();

        HelloWorld obj3 = (HelloWorld) context.getBean("helloWorld");
        obj3.setName("WORLD");
        obj3.printHello();
    }

}
