package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

	public void exequteUpdate(String query, PreparedStatementSetter pss) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			pss.setParameters(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new DataAccessException(e2);
			}
		}
	}

	public void exequteUpdate(String query, Object... parameters) {
		exequteUpdate(query, CreatePreparedStatementSetter(parameters));
	}

	public <T> T executeQuery(String query, RowMapper<T> rm, PreparedStatementSetter pss) {

		List<T> list = list(query, rm, pss);
		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	public <T> List<T> list(String query, RowMapper<T> rm, PreparedStatementSetter pss) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = ConnectionManager.getConnection();
			pstmt = connection.prepareStatement(query);
			pss.setParameters(pstmt);

			rs = pstmt.executeQuery();

			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(rm.mapRow(rs));
			}
			return list;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e2) {
				throw new DataAccessException(e2);
			}

		}
	}

	public <T> List<T> list(String query, RowMapper<T> rm, Object... parameters) {
		return list(query, rm, CreatePreparedStatementSetter(parameters));
	}

	public <T> T executeQuery(String query, RowMapper<T> rm, Object... parameters) {
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
