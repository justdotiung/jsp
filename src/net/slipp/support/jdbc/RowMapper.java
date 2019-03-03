package net.slipp.support.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface //람다식 표현 리팩토링
public interface RowMapper<T> {
	T mapRow(ResultSet rs) throws SQLException;
}
