package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloWorld {

    private String name;

    // HelloWorld has a dependency on WorldCountries
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

    // Setter is required in order to resolve the dependency
    public void setCountries(WorldCountries countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "HelloWorld{" + "name=" + name + ", " + countries + "}";
    }

}
