package net.silpp.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;

	@Before // before jUnit���� �����׽�Ʈ�� �׻� ������ �����ϴ� ������̼�.
	public void setup() {
		userDao = new UserDAO();
	}

	@Test // db���� �׽�Ʈ
	public void test() {
		Connection con = userDao.getConnection();
		assertNotNull(con);
	}

	@Test // db�� ���� �߰� �׽�Ʈ
	public void addUser() throws Exception {
//		userDao.addUser(UserTest.TEST_USER);
	}

	@Test // db ���� ��ġ�ϴ��� �׽�Ʈ.
	public void findByUserId() throws Exception {
		User user = userDao.findByUserId("userId");
		assertEquals(UserTest.TEST_USER, user);

	}
}
