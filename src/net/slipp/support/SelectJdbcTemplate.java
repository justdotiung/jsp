package net.slipp.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//���ø� �޼��� ����(�����ϱ� ����� �޼��带 �߻� Ŭ������ ����� ������ ���� ) Ȱ��
public abstract class SelectJdbcTemplate {
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
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException ;
}
