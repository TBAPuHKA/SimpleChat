package service;

import util.dao.DaoFactory;
import util.dao.UserDao;
import model.User;

public class LoginService {

    private final UserDao userDao;

    public LoginService() {
        DaoFactory daoFactory = DaoFactory.getInstance(1);
        assert daoFactory != null;
        this.userDao = daoFactory.getUserDao();
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public void insertUser (User user) {
        userDao.insertUser(user);
    }
}
