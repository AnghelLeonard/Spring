package org.spring.hello;

import java.util.List;

/**
 *
 * @author Constantin Alin
 */
public class WorldCountries {

    private List<String> countries;

    public WorldCountries() {
        System.out.println("WorldCountries constructor invoked..");
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "WorldCountries{" + "countries=" + countries + '}';
    }

}
