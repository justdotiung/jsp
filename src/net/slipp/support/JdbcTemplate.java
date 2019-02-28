package net.slipp.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// ∏Æ∆—≈‰∏µ 
public class JdbcTemplate {

	public void exequteUpdate(String query, PreparedStatementSetter pss) throws SQLException {
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

	public void exequteUpdate(String query, Object... parameters) throws SQLException {
		exequteUpdate(query, CreatePreparedStatementSetter(parameters));
	}

	public <T> T executeQuery(String query, RowMapper<T> rm, PreparedStatementSetter pss) throws SQLException {

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

	public <T> T executeQuery(String query, RowMapper<T> rm, Object... parameters) throws SQLException {
		return executeQuery(query, rm, CreatePreparedStatementSetter(parameters));
	}

	private PreparedStatementSetter CreatePreparedStatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i + 1, parameters[i]);
				}
			}
		};
	}
}
