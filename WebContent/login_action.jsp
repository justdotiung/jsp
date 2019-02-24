
<%@page import="net.silpp.user.PasswordMismatchException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.silpp.db.DataBase"  %>
<%@page import="net.silpp.user.User"  %>
<%@page import="net.silpp.user.UserNotFoundException"%>


<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	// 비밀번호가 다를경우. 
	try{
		User.login(userId, password);
		session.setAttribute("userId", userId);		
		
		response.sendRedirect("ex1.jsp");
	}catch(UserNotFoundException e){
		request.setAttribute("errorMessage", "아이디가 존재하지 않습니다. 다시 로그인 하세요");
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}catch(PasswordMismatchException e2){
		request.setAttribute("errorMessage", "비밀번호가 틀렸습니다.");
		RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	
%>