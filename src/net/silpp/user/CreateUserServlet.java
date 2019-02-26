package net.silpp.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/create")
public class CreateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		// ���� �����. 4~12 ����,������/���ڸ� ���
		String userId= request.getParameter("userId");
		// 2~10 ����
		String password= request.getParameter("password");
		String name= request.getParameter("name");
		String email= request.getParameter("email");

		User user = new User(userId,password,name,email);
		UserDAO dao = new UserDAO();
		
		try {
			dao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/slipp/ex1.jsp");
	}
	
	

}