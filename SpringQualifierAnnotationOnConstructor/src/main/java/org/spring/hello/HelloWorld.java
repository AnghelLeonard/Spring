package org.spring.hello;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    // HelloWorld has a dependency on WorldCountries
    private WorldCountries countries;

    // The constructor so that Spring container can inject WorldCountries
    public HelloWorld(@Qualifier("foo") WorldCountries countries) {
        this.countries = countries;

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
