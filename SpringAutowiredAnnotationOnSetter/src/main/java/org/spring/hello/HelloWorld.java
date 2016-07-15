package org.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    // HelloWorld has a dependency on WorldCountries
    // The property name MUST have the same name as the dependent bean id
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

    public WorldCountries getCountries() {
        return countries;
    }

    @Autowired  // Setter is required in order to resolve the dependency
    public void setCountries(WorldCountries countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "HelloWorld{" + "name=" + name + ", " + countries + "}";
    }

}
