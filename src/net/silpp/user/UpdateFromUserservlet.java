package net.silpp.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.catalina.connector.Request;


@WebServlet("/updateform")
public class UpdateFromUserservlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//중복제거
		String userId = SessionUtils.getStringValue(session, LoginSevlet.SESSION_USER_ID);

		if(userId ==null) {
			resp.sendRedirect("/slipp/ex1.jsp");
			return ;
		}
		
		System.out.println("userId: "+userId);
		UserDAO dao = new UserDAO();
		try {
			User user = dao.findByUserId(userId);
			req.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/form.jsp"); // form과 update_form의 중복 리팩토리중.
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
