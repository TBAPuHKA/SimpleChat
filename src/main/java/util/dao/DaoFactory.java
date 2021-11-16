package util.dao;

import util.dao.mySQL.MySQLDAOFactory;

public abstract class DaoFactory {
	
	public final static int MY_SQL = 1;
	
	public static DaoFactory getInstance(int sourceType) {
		switch (sourceType) {
		case MY_SQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract UserDao getUserDao();

	public abstract  MessageDAO getMessageDAO();

}
