package org.spring.hello;

import java.text.DecimalFormat;

/**
 *
 * @author Constantin Alin
 */
public class HelloRomania {

    private String continent;
    private String country;
    private Integer population;

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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void printHello() {
        System.out.println("Hello " + country + " (" + continent + ")!");
    }

    @Override
    public String toString() {
        return "HelloRomania{" + "continent=" + continent + ", country=" + country
                + ", population=" + new DecimalFormat("###,###.###").format(population) + '}';
    }

}
