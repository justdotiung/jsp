package net.slipp.support.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
//��쿡 ���� PreparedStatement�� ����� ���� set�ؾ�� ��쵵 �߻� 
//���̺귯���� �������� ���� �������̽��� ����д�.
public interface PreparedStatementSetter {
	 void setParameters(PreparedStatement pstmt) throws SQLException;
}
