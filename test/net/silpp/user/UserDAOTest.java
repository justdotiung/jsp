package net.silpp.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	private UserDAO userDao;

	@Before // before jUnit���� �����׽�Ʈ�� �׻� ������ �����ϴ� ������̼�.
	public void setup() {
		userDao = new UserDAO();
	}

	@Test // db�� ���� �߰� �׽�Ʈ
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);

		User dbUser = userDao.findByUserId(user.getUserId());
		assertEquals(user, dbUser);

		// db ���� ���� �׽�Ʈ
		User updateUser = new User(user.getUserId(), "uPassword", "ujj", "u@adsf.co");
		userDao.updateUser(updateUser);
		dbUser = userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbUser);
	}

	@Test // db�� ���� ���̵� �������� �ʴ°�� �׽�Ʈ.
	public void notExistUserId() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}

	@Test // ����� ��� ��ȸ �׽�Ʈ
	public void findUsers() throws Exception {
		List<User> users = userDao.findUsers();
		assertTrue(users.size() > 0);
		logger.debug("User :{}", users);
	}
}
