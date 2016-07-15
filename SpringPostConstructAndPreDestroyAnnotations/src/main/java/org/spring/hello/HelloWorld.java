package org.spring.hello;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    public HelloWorld() {
        System.out.println("Constructor invoked..");
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean initialization..");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destruction..");
    }

}
