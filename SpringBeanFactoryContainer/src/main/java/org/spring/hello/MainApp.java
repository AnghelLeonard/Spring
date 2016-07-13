package org.spring.hello;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author Constantin Alin
 */
public class MainApp {

    public static void main(String[] args) {

        GenericApplicationContext ctx = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ctx);
        reader.loadBeanDefinitions(new ClassPathResource("/spring-beans.xml"));
        ctx.refresh();

        HelloWorld obj = (HelloWorld) ctx.getBean("helloBean");
        obj.printHello();

        HelloWorld obj2 = (HelloWorld) ctx.getBean("helloBean");
        obj2.printHello();
    }

}
