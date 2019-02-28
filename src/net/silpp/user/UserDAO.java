package net.silpp.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.JdbcTemplate;
import net.slipp.support.PreparesetParameters;

public class UserDAO {

	/**
	 * �̽� - ���ø� �޼��� Ŭ�������� �������� ���� ���� ���������ʾƵ� �Ǵ� �߻�޼��� ����. �ذ�å 1. Jdbc�� �߻�޼������� Ŭ���� ��
	 * �߻�޼��带 ������ interface�� �и�.
	 */
	public void addUser(User user) throws SQLException {
		PreparesetParameters pss = new PreparesetParameters() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());

			}
		};
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {

				return null;
			}

		};

		String query = "insert into userTbl values (?,?,?,?)";
		template.exequteUpdate(query,pss);
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {

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
		return (User) template.executeQuery(query);
	}

	public void removeUser(String userId) throws SQLException {
	PreparesetParameters pss = new PreparesetParameters() {
		
		@Override
		public void setParameters(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, userId);
			
		}
	};
		JdbcTemplate template = new JdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};
		String query = "delete from userTbl where userId = ?";
		template.exequteUpdate(query , pss);
	}

	public void updateUser(User user) throws SQLException {
		PreparesetParameters pss = new PreparesetParameters() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
				
			}
		};
		JdbcTemplate template = new JdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());

			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};
		String query = "update userTbl set password =? ,name =? ,email =? where userId = ?";
		template.exequteUpdate(query , pss );
	}
}
