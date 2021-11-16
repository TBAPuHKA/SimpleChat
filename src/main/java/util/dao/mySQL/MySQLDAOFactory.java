package util.dao.mySQL;

import util.dao.DaoFactory;
import util.dao.MessageDAO;
import util.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class MySQLDAOFactory extends DaoFactory {

	private static String host;
	private static String dbname;
	private static String user;
	private static String pass;
	private static String driver;

	static {
		try {
			FileInputStream fileInputStream = new FileInputStream(String.valueOf(MySQLDAOFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath())+ "config/SQLconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			host = properties.getProperty("db.host");
			dbname = properties.getProperty("db.dbname");
			user = properties.getProperty("db.user");
			pass = properties.getProperty("db.pass");
			driver = properties.getProperty("db.driver");
			fileInputStream.close();
			log.info("Read data from config | SUCCESS");
		} catch (IOException e) {
			log.error("Read data from config | FAIL");
			e.printStackTrace();
		}
	}

	public MySQLDAOFactory() {

		try {
			Class.forName(driver).newInstance();
			log.info("Create driver | SUCCESS");
		} catch (Exception ex) {
			log.error("Create driver | FAIL");
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(host + dbname + "?" + "user=" + user + "&" + "password=" + pass);
			log.info("Connect to data base | SUCCESS");
		} catch (SQLException ex) {
			log.error("Connect to data base | FAIL");
			ex.printStackTrace();
		}

		return conn;
	}

	@Override
	public UserDao getUserDao() {
		return new MySQLUserDAO(this);
	}

	@Override
	public MessageDAO getMessageDAO() {
		return new MySQLMessageDAO(this);
	}

	;
}