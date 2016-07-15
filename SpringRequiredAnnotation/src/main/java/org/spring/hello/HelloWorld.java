package org.spring.hello;

import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    public HelloWorld() {
        System.out.println("Constructor invoked..");
    }

    @Required   // Indicates that the bean property must be populated at configuration time
    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello " + name + "!");
    }

}
