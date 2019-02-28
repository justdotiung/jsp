package net.silpp.user;


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;

	@Before // before jUnit에서 단위테스트시 항상 선수로 시행하는 어노테이션.
	public void setup() {
		userDao = new UserDAO();
	}

	@Test // db에 유저 추가 테스트
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;  
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId()); 
		assertEquals(user ,dbUser); 
		//db 유저 수정 테스트
		User updateUser = new User(user.getUserId(), "uPassword" , "ujj","u@adsf.co");
		userDao.updateUser(updateUser);
		dbUser = userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbUser);
	}
	
	@Test// db에 유저 아이디 존재하지 않는경우 테스트.
	public void notExistUserId() throws Exception {
		User user = UserTest.TEST_USER;   
		userDao.removeUser(user.getUserId());
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}	
	@Test//사용자 목록 조회
	public void findUsers() throws Exception {
		list
	}
}
