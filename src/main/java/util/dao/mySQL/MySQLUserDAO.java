package util.dao.mySQL;

import util.dao.UserDao;
import model.User;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class MySQLUserDAO implements UserDao {
	private final static String GET_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
	private final static String ADD_USER = "INSERT INTO users (name) VALUES (?)";
	private final MySQLDAOFactory ms;

	public MySQLUserDAO(MySQLDAOFactory ms) {
		this.ms = ms;
	}

	@Override
	public User getUserByName(String name) {
		User user = null;
		Connection conn = ms.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = conn.prepareStatement(GET_USER_BY_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setName(resultSet.getString("name"));
				user.setId(resultSet.getInt("userid"));
			}
			assert user != null;
			log.info(String.format("Saved new user id:[%s] to data base | SUCCESS", user.getId()));

		} catch (SQLException e) {
			log.error(String.format("getUserByName(%s) | Something wrong with connection to DB",name));
			e.printStackTrace();
		} finally {
			try {
				assert resultSet != null;
				resultSet.close();
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				log.error("Something bad with close connections");
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void insertUser(User user) {
		PreparedStatement st = null;
		Connection conn = ms.getConnection();

		try {
			st = conn.prepareStatement(ADD_USER);
			st.setString(1, user.getName());
			st.execute();
			log.info(String.format("Add new user name:[%s] to data base | SUCCESS",user.getName()));
		} catch (SQLException e) {
			log.error(String.format("insertUser(%s) | Something wrong with connection to DB",user.getName()));
			e.printStackTrace();
		} finally {
			try {
				assert st != null;
				st.close();
				conn.close();
			} catch (SQLException e) {
				log.error("Something bad with close connections");
				e.printStackTrace();
			}

		}
	}

}
