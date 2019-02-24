package net.silpp.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;



/**
 * �����׽�Ʈ ���� 
 */
public class UserTest {
	public static User TEST_USER = new User("ie","1234","jj","efd@adsf.co"); //�׽�Ʈ ��ü.
	
	private UserDAO user=new UserDAO();
	@Before
	public void setup() throws Exception {
		user.removeUser(TEST_USER.getUserId());
	}
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("1234"));
	}
	@Test
	public void misMatchPassword() {
		assertFalse(TEST_USER.matchPassword("12342"));
	}
	@Test
	public void login() throws Exception {
		user.addUser(TEST_USER);
		assertTrue(User.login(TEST_USER.getUserId() , TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class) //���̵� �����.
	public void loginWhennotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}

	@Test(expected=PasswordMismatchException.class) //��й�ȣ�� �ٸ����.
	public void loginWhenPasswordMismatch() throws Exception {
		UserDAO dao =new UserDAO();
		dao.addUser(TEST_USER);
		User.login( TEST_USER.getUserId(),"12342");
	}
	
}
