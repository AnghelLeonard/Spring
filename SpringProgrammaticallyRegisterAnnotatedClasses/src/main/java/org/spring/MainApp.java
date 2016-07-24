package org.spring;

import org.spring.bean.HelloWorld;
import org.spring.config.MySpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        /*
         * AnnotationConfigApplicationContext may be instantiated using a no-arg constructor and then
         * configured using the register() method.
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MySpringConfig.class);

        // context.register(AppConfig.class, OtherConfig.class);    // Register other configuration classes

        /*
         * Refresh the persistent representation of the configuration. Must be called in order for the
         * context to fully process the new classes.
         */
        context.refresh();

        HelloWorld obj = (HelloWorld) context.getBean(HelloWorld.class);
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) context.getBean(HelloWorld.class);
        obj2.setName("world");
        obj2.printHello();
    }

}
