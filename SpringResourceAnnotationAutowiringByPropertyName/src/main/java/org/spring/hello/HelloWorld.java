package org.spring.hello;

import javax.annotation.Resource;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    // HelloWorld has a dependency on WorldCountries
    // The property name MUST have the same name as the dependent bean id
    @Resource   // @Resource annotation can also be used on bean property setter methods
    private WorldCountries countries;

    public HelloWorld() {
        System.out.println("HelloWorld constructor invoked..");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelloWorld{" + "name=" + name + ", " + countries + "}";
    }

}
