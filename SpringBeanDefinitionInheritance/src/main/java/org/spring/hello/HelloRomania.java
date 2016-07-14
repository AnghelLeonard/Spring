package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloRomania {

    private String continent;
    private String country;

    public HelloRomania() {
        System.out.println("HelloRomania constructor invoked..");
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void printHello() {
        System.out.println("Hello " + country + " (" + continent + ")!");
    }

    @Override
    public String toString() {
        return "HelloRomania{" + "continent=" + continent + ", country=" + country + '}';
    }

}
