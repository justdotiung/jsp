package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.silpp.user.User;
import net.silpp.user.UserDAO;

/**
 * ���ø� �޼��� ���� Ŭ����
 * JdbcTemplate �� UpdateJdbcTemplate �� ���� 
 * �����׽�Ʈ�� ���������� �Ͽ� ����� ��´�.
 * (overloading �� ����غ���.)
 */
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
	public void exequteUpdate(String query,PreparesetParameters pss) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			pss.setParameters(pstmt);

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

	public Object executeQuery(String query) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			setParameters(pstmt);
			rs = pstmt.executeQuery();

			return mapRow(rs);

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

	public abstract Object mapRow(ResultSet rs) throws SQLException;

	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
