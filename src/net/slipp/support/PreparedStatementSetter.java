package net.slipp.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	 void setParameters(PreparedStatement pstmt) throws SQLException;
}
