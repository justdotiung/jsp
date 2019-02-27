package net.slipp.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
//리팩토리 과정중 단위테스트 클래스 생성및 기존메소드 이동
class ConnectionManagerTest {

	@Test // db연결 테스트
	public void test() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}
}
