package spring.components;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
public class UserServiceImpl implements UserService {
 
    private UserDaoImpl userDao;

    public UserDao getUserDao() {
        return this.userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValidUser(String username, String password) throws SQLException {
        return userDao.isValidUser(username, password);
    }

}
