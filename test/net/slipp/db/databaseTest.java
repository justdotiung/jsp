package net.slipp.db;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import net.silpp.db.DataBase;
import net.silpp.user.User;
import net.silpp.user.UserTest;



public class databaseTest {
	User user = UserTest.TEST_USER;
	@Test
	public void addAndFindWhenExisted() {
	//중복 제거	User user = new User("ie","1234","jj","efd@adsf.co");
		DataBase.addUser(user);
		
		User dbUser = DataBase.findByUserId(user.getUserId());
		assertEquals(user, dbUser);
	}
	public void addAndFindWhenNotExisted() {
		
		User dbUser = DataBase.findByUserId("i");
		assertNull(dbUser);
	}

}
