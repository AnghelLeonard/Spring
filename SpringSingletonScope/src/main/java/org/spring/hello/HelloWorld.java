package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    public HelloWorld() {
        System.out.println("Constructor invoked..");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!" + " hashCode: " + this.hashCode());
    }

}
