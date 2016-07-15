package org.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    // HelloWorld has a dependency on WorldCountries
    private WorldCountries countries;

    /*
     * As of Spring Framework 4.3, the @Autowired on constructor is no longer necessary if the target bean
     * defines ONLY one constructor.
     */
    @Autowired  // The constructor so that Spring container can inject WorldCountries
    public HelloWorld(WorldCountries countries) {
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
