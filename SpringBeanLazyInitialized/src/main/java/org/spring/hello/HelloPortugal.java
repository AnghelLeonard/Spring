package org.spring.hello;

/**
 *
 * @author Constantin Alin
 */
public class HelloPortugal {

    private String country;

    public HelloPortugal() {
        System.out.println("HelloPortugal constructor invoked..");
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
