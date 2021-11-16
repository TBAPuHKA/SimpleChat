package util.dao;

import model.User;

public interface UserDao {

	User getUserByName(String name);

	void insertUser(User user);
}
