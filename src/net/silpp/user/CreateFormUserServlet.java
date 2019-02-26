package net.silpp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/createform")
public class CreateFormUserServlet extends HttpServlet{
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// �� ������ ��ü ���
	req.setAttribute("user", new User()); 
	RequestDispatcher dispatcher = req.getRequestDispatcher("/form.jsp");
	dispatcher.forward(req, resp);
}
}