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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updateform")
public class UpdateFromUserservlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(UpdateFromUserservlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String userId = SessionUtils.getStringValue(session, LoginSevlet.SESSION_USER_ID);

		if (userId == null) {
			resp.sendRedirect("/slipp/ex1.jsp");
			return;
		}
		logger.debug("userId: {}", userId);
		UserDAO dao = new UserDAO();

		User user = dao.findByUserId(userId);
		req.setAttribute("isUpdate", true);
		req.setAttribute("user", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/form.jsp"); // form과 update_form의 중복 리팩토리중.
		dispatcher.forward(req, resp);

	}

}
