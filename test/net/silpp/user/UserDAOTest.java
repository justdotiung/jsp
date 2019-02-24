package net.silpp.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;

	@Before // before jUnit에서 단위테스트시 항상 선수로 시행하는 어노테이션.
	public void setup() {
		userDao = new UserDAO();
	}

	@Test // db연결 테스트
	public void test() {
		Connection con = userDao.getConnection();
		assertNotNull(con);
	}

	@Test // db에 유저 추가 테스트
	public void addUser() throws Exception {
//		userDao.addUser(UserTest.TEST_USER);
	}

	@Test // db 값과 일치하는지 테스트.
	public void findByUserId() throws Exception {
		User user = userDao.findByUserId("userId");
		assertEquals(UserTest.TEST_USER, user);

	}
}
