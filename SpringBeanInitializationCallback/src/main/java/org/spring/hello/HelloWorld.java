package org.spring.hello;

import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld implements InitializingBean {

    private String name;

    public HelloWorld() {
        System.out.println("Constructor invoked..");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean initialization..");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!");
    }

}
