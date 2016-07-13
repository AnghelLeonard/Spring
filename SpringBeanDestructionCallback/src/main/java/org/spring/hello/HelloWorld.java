package org.spring.hello;

import org.springframework.beans.factory.DisposableBean;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld implements DisposableBean {

    private String name;

    public HelloWorld() {
        System.out.println("Constructor invoked..");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean destruction..");
    }

}
