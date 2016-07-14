package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloRomania {

    private String country;

    public HelloRomania() {
        System.out.println("HelloRomania constructor invoked..");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void printHello() {
        System.out.println("Hello " + country + "!");
    }

}
