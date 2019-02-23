<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/commons/_head.jspf"%>
</head>
<body>
	<%@include file="/commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
					<div class="page-header">
						<h1>로그인</h1>

						<form class="form-horiaontal" action="login_action.jsp"
							method="post">
							<div class="control-group">
								<label class="control-label" for="userId">사용자 아이디 </label>
								<div class="controls">
									<input type="text" name="userId" value="" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="password">비밀번호</label>
								<div class="controls">
									<input type="password" name="password" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">로그인</button>
								</div>
							</div>
						</form>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>