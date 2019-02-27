package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.silpp.user.User;
import net.silpp.user.UserDAO;

public abstract class JdbcTemplate {
	public Connection getConnection() {
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

	public void exequteUpdate(String query) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = getConnection().prepareStatement(query);
			setParameters(pstmt);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException ;
}
