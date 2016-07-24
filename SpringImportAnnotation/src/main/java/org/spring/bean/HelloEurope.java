package org.spring.bean;

/**
 *
 * @author Constantin Alin
 */
public class HelloEurope {

    private String name;

    public HelloEurope() {
        System.out.println("HelloEurope constructor invoked..");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!");
    }

}
