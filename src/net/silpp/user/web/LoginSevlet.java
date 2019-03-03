package net.silpp.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.silpp.user.PasswordMismatchException;
import net.silpp.user.User;
import net.silpp.user.UserNotFoundException;

@WebServlet("/login")
public class LoginSevlet extends HttpServlet {
	public static final String SESSION_USER_ID = "userId"; //�ٸ������� ����Ҽ��������� ���������� ����.
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String userId = request.getParameter(SESSION_USER_ID);
		String password = request.getParameter("password");
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());

		try{
			User.login(userId, password);
			HttpSession session = request.getSession(); 
			session.setAttribute(SESSION_USER_ID, userId);		
			
			response.sendRedirect("/slipp/ex1.jsp");
		}catch(UserNotFoundException e){
			forwardJSP(request, response, "���̵� �������� �ʽ��ϴ�. �ٽ� �α��� �ϼ���");
		}catch(PasswordMismatchException e2){
			forwardJSP(request, response, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
		
		
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response , String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

}
