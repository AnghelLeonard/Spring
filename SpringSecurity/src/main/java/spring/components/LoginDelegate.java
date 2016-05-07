package spring.components;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
public class LoginDelegate {
   
    private UserService userService;

    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean isValidUser(String username, String password) throws SQLException {
        return userService.isValidUser(username, password);
    }
}
