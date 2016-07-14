package org.spring.hello;

import java.util.Map;

/**
 *
 * @author Constantin Alin
 */
public class Countries {

    private Map<Integer, String> countriesMap;

    public Countries() {
        System.out.println("Constructor invoked..");
    }

    public Map<Integer, String> getCountriesMap() {
        return countriesMap;
    }

    public void setCountriesMap(Map<Integer, String> countriesMap) {
        this.countriesMap = countriesMap;
    }

    @Override
    public String toString() {
        return "Countries{" + "countries=" + countriesMap + '}';
    }

}
