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

        UserPreferences obj = (UserPreferences) context.getBean("user");
        System.out.println(obj);

        UserPreferences obj2 = (UserPreferences) context.getBean("user");
        System.out.println(obj2);
    }

}
