package net.silpp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

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

	public void addUser(User user) throws SQLException {
		String query = "insert into userTbl values (?,?,?,?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());

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

	public User findByUserId(String userId) throws SQLException {
		String query = "select * from usertbl where userId = ? ";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			connection = getConnection();
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));

		} catch (Exception e) {
			e.printStackTrace();
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
		return user;
	}

	public void removeUser(String userId) throws SQLException {
		String query = "delete from userTbl where userId = ?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, userId);

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

	public void updateUser(User user) throws SQLException {
		String query = "update userTbl set password =? ,name =? ,email =? where userId = ?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());

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

}
