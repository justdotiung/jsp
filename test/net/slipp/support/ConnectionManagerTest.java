package net.slipp.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
//�����丮 ������ �����׽�Ʈ Ŭ���� ������ �����޼ҵ� �̵�
class ConnectionManagerTest {

	@Test // db���� �׽�Ʈ
	public void test() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}
}
