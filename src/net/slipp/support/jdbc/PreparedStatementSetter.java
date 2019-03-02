package net.slipp.support.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
//경우에 따라 PreparedStatement를 사용해 값을 set해얗는 경우도 발생 
//라이브러리의 유연성을 위해 인터페이스는 살려둔다.
public interface PreparedStatementSetter {
	 void setParameters(PreparedStatement pstmt) throws SQLException;
}
