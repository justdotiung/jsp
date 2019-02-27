package net.silpp.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.JdbcTemplate;
import net.slipp.support.SelectJdbcTemplate;

public class UserDAO {

	public void addUser(User user) throws SQLException {

		JdbcTemplate template = new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

		};

		String query = "insert into userTbl values (?,?,?,?)";
		template.exequteUpdate(query);
	}

	/**
	 * 리팩토리중 userId 달라지는 부분을 메소드 추출은 한다 (resultSet ,query) 매핑 부분(resultSet에서 데이터얻어서
	 * user객채로 만들어 반환하는 부분)을 메소드 추출 명확하게 개발자가 개발해야하는 부분과 라이브러리 부분을 나눈다. 
	 * 하나의 클래스로 만든다.
	 * 리팩토링 한다.
	 */
	public User findByUserId(String userId) throws SQLException {
		SelectJdbcTemplate template = new SelectJdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);

			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));

			}
		};

		String query = "select * from usertbl where userId = ? ";
		return (User)template.executeQuery(query);
	}

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
