package spring.beans;

import java.sql.SQLException;

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
