package net.silpp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.JdbcTemplate;

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
		// 새로운 추상클래스 생성 및 접근
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

		};// 익명클래스 로 접근 (1회성)

		String query = "insert into userTbl values (?,?,?,?)";
		template.exequteUpdate(query);
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
	//리팩토링 한다.
	public void removeUser(String userId) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		String query = "delete from userTbl where userId = ?";
		template.exequteUpdate(query);
	}
	//리팩토링 한다.
	public void updateUser(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());

			}
		};
		String query = "update userTbl set password =? ,name =? ,email =? where userId = ?";
		template.exequteUpdate(query);
	}
}
