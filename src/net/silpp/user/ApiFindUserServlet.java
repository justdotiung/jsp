package net.silpp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/find")//gson 사용 테스트.
public class ApiFindUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		if(userId == null) {
			resp.sendRedirect("/slipp/ex1.jsp");
		return ;
	}
	UserDAO dao =new UserDAO();
	try {
		User user = dao.findByUserId(userId);
		if(user == null) {
			return ;
		}
	    
		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		final Gson gson = builder.create();
		
		String jsonData = gson.toJson(user);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("jsondata :" +jsonData);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
}
