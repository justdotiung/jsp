package net.silpp.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")//(�������� ������ �ʰ�) ���� ȣ���  ���� ������ �Ҽ��ִ� ���������ִ�. 
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session =req.getSession();
		//login ���� ���.
		Object obj = session.getAttribute(LoginSevlet.SESSION_USER_ID);
		if(obj==null) {
			resp.sendRedirect("/slipp/ex1.jsp");
			return ;
		}
		//������ id���� ����id���� ��ġ�ϴ� ����.
		String sessionUserId = (String)obj;
		String userId = req.getParameter("userId");
		if(!sessionUserId.equals(userId)) {
			
			resp.sendRedirect("/slipp/ex1.jsp");
			return ;
		}
		
		String password= req.getParameter("password");
		String name= req.getParameter("name");
		String email= req.getParameter("email");

		User user = new User(userId,password,name,email);
		UserDAO dao = new UserDAO();
		try {
			dao.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}resp.sendRedirect("/slipp/ex1.jsp");
	}
}
