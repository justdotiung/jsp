package net.silpp.user;

import static org.junit.Assert.*;

import org.junit.Test;

import net.silpp.db.DataBase;

/**
 * @author jang
 * 단위테스트 검증 
 */
public class UserTest {
	public static User TEST_USER = new User("ie","1234","jj","efd@adsf.co"); //테스트 객체.
	@Test
	public void matchPassword() {
	//중복 제거	User user = new User("ie","1234","jj","efd@adsf.co");
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
	
	@Test(expected=UserNotFoundException.class) //아이디가 없경우.
	public void loginWhennotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}

	@Test(expected=PasswordMismatchException.class) //비밀번호가 다를경우.
	public void loginWhenPasswordMismatch() throws Exception {
		
		DataBase.addUser(TEST_USER);
		User.login( TEST_USER.getUserId(),"12342");
	}
	
}
