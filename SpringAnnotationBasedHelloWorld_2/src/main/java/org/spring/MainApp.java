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

        // 'helloBean' is the method name from MySpringConfig, equivalent to <bean id="helloBean" .. />
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();

        // 'helloWorld' is the method name from MySpringConfig, equivalent to <bean id="helloWorld" .. />
        HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld");
        obj2.printHello();
    }

}
