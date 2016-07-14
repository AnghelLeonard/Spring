package org.spring.hello;

import java.util.Set;

/**
 *
 * @author Constantin Alin
 */
public class Countries {

    private Set<String> countriesSet;

    public Countries() {
        System.out.println("Constructor invoked..");
    }

    public Set<String> getCountriesSet() {
        return countriesSet;
    }

    public void setCountriesSet(Set<String> countriesSet) {
        this.countriesSet = countriesSet;
    }

    @Override
    public String toString() {
        return "Countries{" + "countries=" + countriesSet + '}';
    }

}
