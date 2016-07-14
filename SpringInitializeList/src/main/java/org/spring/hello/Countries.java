package org.spring.hello;

import java.util.List;

/**
 *
 * @author Constantin Alin
 */
public class Countries {

    private List<String> countriesList;

    public Countries() {
        System.out.println("Constructor invoked..");
    }

    public List<String> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(List<String> countriesList) {
        this.countriesList = countriesList;
    }

    @Override
    public String toString() {
        return "Countries{" + "countries=" + countriesList + '}';
    }

}
