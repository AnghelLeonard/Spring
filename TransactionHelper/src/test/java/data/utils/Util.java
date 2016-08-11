package data.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anghel Leonard
 */
public final class Util {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Util.class.getName());
    
    private static final String PROPERTIES_FILE_NOT_FOUND
            = "Cannot find the properties file, %s. Please ensure that you have this file in classpath.";
     private static final String PROPERTIES_FILE_FOUND
            = "Properties file jdbc-mysql.properties was successfully found ...";

    public static Properties readAllProperties(String filename) {

        InputStream input = null;
        Properties properties = new Properties();

        try {
           
            input = Util.class.getClassLoader().getResourceAsStream(filename);

            if (input == null) {
                LOG.error(String.format(PROPERTIES_FILE_NOT_FOUND, filename));
                throw new FileNotFoundException(String.format(PROPERTIES_FILE_NOT_FOUND, filename));
            } else {
                LOG.info(PROPERTIES_FILE_FOUND);
            }

            properties.load(input);
            
            return properties;

        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    }

}
