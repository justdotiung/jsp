package net.silpp.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.silpp.db.DataBase;
//¸®
@WebServlet("/users/save")
public class SaveUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");

		String userId= request.getParameter("userId");
		String password= request.getParameter("password");
		String name= request.getParameter("name");
		String email= request.getParameter("email");

		User user = new User(userId,password,name,email);
		DataBase.addUser(user);
		
		response.sendRedirect("/slipp/ex1.jsp");
	}
	
	

}
