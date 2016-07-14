package org.spring.hello;

import java.util.Properties;

/**
 *
 * @author Constantin Alin
 */
public class UserPreferences {

    private Properties properties;

    public UserPreferences() {
        System.out.println("Constructor invoked..");
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "UserPreferences{" + "properties=" + properties + '}';
    }

}
