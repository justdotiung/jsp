package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.silpp.user.User;
import net.silpp.user.UserDAO;

public abstract class JdbcTemplate {
	public void exequteUpdate(String query) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
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
