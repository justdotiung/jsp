package net.silpp.user;

import static org.junit.Assert.*;

import org.junit.Test;

import net.silpp.db.DataBase;

/**
 * @author jang
 * �����׽�Ʈ ���� 
 */
public class UserTest {
	public static User TEST_USER = new User("ie","1234","jj","efd@adsf.co"); //�׽�Ʈ ��ü.
	@Test
	public void matchPassword() {
	//�ߺ� ����	User user = new User("ie","1234","jj","efd@adsf.co");
		assertTrue(TEST_USER.matchPassword("1234"));
	}
	@Test
	public void misMatchPassword() {
	//	User user = new User("ie","1234","jj","efd@adsf.co");
		assertFalse(TEST_USER.matchPassword("12342"));
	}
	@Test
	public void login() throws Exception {
		DataBase.addUser(TEST_USER);
	
		assertTrue(User.login(TEST_USER.getUserId() , TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class) //���̵� �����.
	public void loginWhennotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}

	@Test(expected=PasswordMismatchException.class) //��й�ȣ�� �ٸ����.
	public void loginWhenPasswordMismatch() throws Exception {
		
		DataBase.addUser(TEST_USER);
		User.login( TEST_USER.getUserId(),"12342");
	}
	
}
