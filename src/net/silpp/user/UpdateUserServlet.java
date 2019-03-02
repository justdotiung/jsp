package net.silpp.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import net.slipp.support.MyValidatorFactory;

@WebServlet("/update") // (브라우저를 통하지 않고) 직접 호출시 서블릿 관여를 할수있는 문제점이있다.
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginSevlet.SESSION_USER_ID);
		if (sessionUserId == null) {
			resp.sendRedirect("/slipp/ex1.jsp");
			return;
		}
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
		}

		if (!user.isSameUserId(sessionUserId)) {
			resp.sendRedirect("/slipp/ex1.jsp");
			return;
		}

		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			req.setAttribute("isUpdate", true);
			req.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(req, resp, errorMessage);
			return;
		}
		UserDAO dao = new UserDAO();
		dao.updateUser(user);
		resp.sendRedirect("/slipp/ex1.jsp");
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
		dispatcher.forward(request, response);
	}

}
