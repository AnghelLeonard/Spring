package spring.beans;

import java.sql.SQLException;

/**
 *
 * @author Anghel Leonard
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return this.userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValidUser(String username, String password) throws SQLException {
        return userDao.isValidUser(username, password);
    }

}
