package net.silpp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		

		try{
			User.login(userId, password);
			HttpSession session = request.getSession(); 
			session.setAttribute("userId", userId);		
			
			response.sendRedirect("/slipp/ex1.jsp");
		}catch(UserNotFoundException e){
			forwardJSP(request, response, "아이디가 존재하지 않습니다. 다시 로그인 하세요");
		}catch(PasswordMismatchException e2){
			forwardJSP(request, response, "비밀번호가 틀렸습니다.");
		}
		
		
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response , String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

}
