package net.silpp.user;


import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static boolean IsEmpty(HttpSession session, String key) {
		Object obj = session.getAttribute(key);
		if (obj == null) {
			return true;
		}
		return false;
	}
	public static String getStringValue(HttpSession session, String key) {
		if(IsEmpty(session, key)) {
			return null;
		}
		return (String)session.getAttribute(key);
	}

}
