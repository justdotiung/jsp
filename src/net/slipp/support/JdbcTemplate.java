package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.silpp.user.User;
import net.silpp.user.UserDAO;

/**
 * 가변인자 사용 
 * 가변인자는 항상마지막에 와야한다.
 * UserDAO의 SetprepareStatement 리팩토링
 */
public class JdbcTemplate {

	public void exequteUpdate(String query, Object... parameters) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setObject(i+1, parameters[i]);
			}
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

	public <T> T executeQuery(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			pss.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return rm.mapRow(rs);

		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public <T> T executeQuery(String query, RowMapper<T> rm, Object...parameters) throws SQLException {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setObject(i+1, parameters[i]);
			}
			
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return rm.mapRow(rs);
			
		} finally {
			try {
				
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
