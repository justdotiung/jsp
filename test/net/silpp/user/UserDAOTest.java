package net.silpp.user;


import static org.junit.Assert.*;

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
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;  
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId()); 
		assertEquals(user ,dbUser); 
	}
	
	@Test
	public void notExistUserId() throws Exception {
		User user = UserTest.TEST_USER;   
		userDao.removeUser(user.getUserId());

		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}	
}
