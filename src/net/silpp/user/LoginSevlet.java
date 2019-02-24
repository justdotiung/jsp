package net.silpp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// ��й�ȣ�� �ٸ����. 
		try{
			User.login(userId, password);
			HttpSession session = request.getSession(); //request �ν��Ͻ����� ���� �� ������ϴ� session��ü ����.
			session.setAttribute("userId", userId);		
			
			response.sendRedirect("/slipp/ex1.jsp");
		}catch(UserNotFoundException e){
			forwardJSP(request, response, "���̵� �������� �ʽ��ϴ�. �ٽ� �α��� �ϼ���");
		}catch(PasswordMismatchException e2){
			forwardJSP(request, response, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
//			request.setAttribute("errorMessage", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
//			RequestDispatcher dispatcher= request.getRequestDispatcher("/slipp/login.jsp");
//			dispatcher.forward(request, response);  
			//�����丵.
		}
		
		
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response , String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

}