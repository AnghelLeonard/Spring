package spring.components;

import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
public interface UserService {

    public boolean isValidUser(String username, String password) throws SQLException;
}
