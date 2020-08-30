<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<c:if test="${param.incorrectAccount != null}">
		<div class="alert alert-danger">	
			Username or password incorrect
		</div>
	</c:if>
	<c:if test="${param.accessDenied != null}">
		<div class="alert alert-danger">	
			you Not authorize
		</div>
	</c:if>
	<br>
	<form action="j_spring_security_login" method="post">
		Username: <input type="text" name="j_username" placeholder="Tên đăng nhập"/>
		<br>
		Password: <input type="text" name="j_password" placeholder="Mật khẩu"/>
		<br>
		
		
		<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
		
		<button type="submit">Đăng nhập</button>
	</form>

</body>
</html>