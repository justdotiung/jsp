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
		PreparedStatement pstat = getConnection().prepareStatement(query);
		pstat.setString(1, user.getUserId());
		pstat.setString(2, user.getPassword());
		pstat.setString(3, user.getName());
		pstat.setString(4, user.getEmail());

		pstat.executeUpdate();

	}

	public User findByUserId(String userId) throws SQLException {
		String query = "select * from usertbl where userId = ? ";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

				
				return  new User(rs.getString("userId"), 
						rs.getString("password"),
						rs.getString("name"), 
						rs.getString("email"));

			 
	}

	public void removeUser(String userId) throws SQLException {
		String query = "delete from userTbl where userId = ?";
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, userId);
		
		stmt.executeUpdate();
	
	}


}
