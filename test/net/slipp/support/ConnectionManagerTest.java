package net.slipp.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

	@Test // db연결 테스트
	public void test() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}
}
