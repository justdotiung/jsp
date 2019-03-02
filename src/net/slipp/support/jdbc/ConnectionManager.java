package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() {
		try {
			String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

			String ID = "scott";

			String PW = "1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
