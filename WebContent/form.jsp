<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>

<%@include file="/commons/_head.jspf"%>

</head>
<%@include file="/commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
				<c:choose><!-- 컨트롤러 이용한 form.jsp 와 update_form.jsp 의 중복제거 병합 및 리팩토링 -->
				<c:when test="${empty user.userId}">
				<c:set var="url" value="create"/>
				<c:set var="submit" value="회원가입"/>
					<h1>회원가입</h1>
				</c:when>
				<c:otherwise>
				<c:set var="url" value="update"/>
				<c:set var="submit" value="회원수정"/>
				<h1>개인정보 수정</h1>
				</c:otherwise>
				</c:choose>	
				</div>
			
				
				<form class="form-horizontal" action="${url}" method="post">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
						<c:choose>
						<c:when test="${empty user.userId}">
							<input type="text" name="userId" value="${user.userId}" />
						</c:when>
						<c:otherwise>
						<input type="hidden" name="userId" value="${user.userId}"/><!-- hidden userId객체 데이터값 을 가져올수있다. -->
						${user.userId}
						</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="password" name="password" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<input type="text" id="name" name="name" value="${user.name}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<input type="text" id="email" name="email" value="${user.email}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">${submit}</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>