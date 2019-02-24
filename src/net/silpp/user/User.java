package net.silpp.user;

import net.silpp.db.DataBase;

/**
 * @author jang
 *  map 의 value 값에 저장할수있는 클래스를 만든다.
 */
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public User(String userId, String password, String name, String email) {
		
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUserId() {
		return userId;
	}

	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}

	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException {
		User user = DataBase.findByUserId(userId);
		if(user ==null) {
			throw new UserNotFoundException();
		}
			
		if(!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}


	
}
