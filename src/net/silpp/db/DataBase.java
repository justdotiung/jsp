package net.silpp.db;

import java.util.HashMap;
import java.util.Map;

import net.silpp.user.User;

public class DataBase {
	private static Map<String ,User> users = new HashMap<>();
	
	public static void addUser(User user) {
		System.out.println(user); //toString 으로 user 객체 데이터 확인.
		users.put(user.getUserId(), user);
	}

	public static User findByUserId(String userId) {
		return users.get(userId);
	}//
}
