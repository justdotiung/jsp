package net.silpp.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.slipp.support.jdbc.JdbcTemplate;
import net.slipp.support.jdbc.RowMapper;

public class UserDAO {

	public void addUser(User user)  {
		JdbcTemplate template = new JdbcTemplate();
		String query = "insert into userTbl values (?,?,?,?)";
		template.exequteUpdate(query, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId)  {
		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {

				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		JdbcTemplate template = new JdbcTemplate();

		String query = "select * from usertbl where userId = ? ";
		return template.executeQuery(query, rm, userId);
	}

	public void removeUser(String userId)  {
		JdbcTemplate template = new JdbcTemplate();
		String query = "delete from userTbl where userId = ?";
		template.exequteUpdate(query, userId);
	}

	public void updateUser(User user)  {
		JdbcTemplate template = new JdbcTemplate();
		String query = "update userTbl set password =? ,name =? ,email =? where userId = ?";
		template.exequteUpdate(query, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findUsers()  {
		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return  new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		JdbcTemplate template = new JdbcTemplate();
		String query = "select * from userTbl";

		return template.list(query, rm);

	}
}
