<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="net.silpp.user.User" %>
<%@page import="net.silpp.db.DataBase" %>

<% 
	request.setCharacterEncoding("UTF-8");

	String userId= request.getParameter("userId");
	String password= request.getParameter("password");
	String name= request.getParameter("name");
	String email= request.getParameter("email");

	User user = new User(userId,password,name,email);
	DataBase.addUser(user);
	

	response.sendRedirect("ex1.jsp");
%>	