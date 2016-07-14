package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloEurope {

    private String continent;

    public HelloEurope() {
        System.out.println("HelloEurope constructor invoked..");
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void printHello() {
        System.out.println("Hello " + continent + "!");
    }

    @Override
    public String toString() {
        return "HelloEurope{" + "continent=" + continent + '}';
    }

}
