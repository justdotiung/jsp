package net.silpp.user;


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;

	@Before // before jUnit���� �����׽�Ʈ�� �׻� ������ �����ϴ� ������̼�.
	public void setup() {
		userDao = new UserDAO();
	}

	@Test // db�� ���� �߰� �׽�Ʈ
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;  
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId()); 
		assertEquals(user ,dbUser); 
		//db ���� ���� �׽�Ʈ
		User updateUser = new User(user.getUserId(), "uPassword" , "ujj","u@adsf.co");
		userDao.updateUser(updateUser);
		dbUser = userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbUser);
	}
	
	@Test// db�� ���� ���̵� �������� �ʴ°�� �׽�Ʈ.
	public void notExistUserId() throws Exception {
		User user = UserTest.TEST_USER;   
		userDao.removeUser(user.getUserId());
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}	
	@Test//����� ��� ��ȸ
	public void findUsers() throws Exception {
		list
	}
}
