package ua.lviv.lgs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

	private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DATABASE_URL = "jdbc:mysql://localhost/int_shop?useUnicode=true&serverTimezone=UTC";
	private static String USER = "newuser";
	private static String PASSWORD = "321321321_";

	public static Connection connect()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER).newInstance();

		return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
	}

}
