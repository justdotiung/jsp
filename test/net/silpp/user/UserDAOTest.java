package net.silpp.user;


import static org.junit.Assert.*;

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
		User user = UserTest.TEST_USER;  //duplicate Exception 
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
	}

	@Test // db ���� ��ġ�ϴ��� �׽�Ʈ.
	public void findByUserId() throws Exception {
		User user = userDao.findByUserId("userId");
		assertEquals(user, UserTest.TEST_USER); //expected / actual

	}
}
