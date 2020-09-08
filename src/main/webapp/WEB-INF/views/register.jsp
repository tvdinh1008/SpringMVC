<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" action="dang-ky" modelAttribute="customerModel">
		<form:input type="hidden" path="pojo.id" id="id"/>
		<table>
			<tr>
				<td><label for="name">Name: </label> </td>
				<td><form:input path="pojo.name" id="name"/></td>
				<td><form:errors path="pojo.name" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="username">Username: </label> </td>
				<td><form:input path="pojo.username" id="username"/></td>
				<td><form:errors path="pojo.username" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="email">Email: </label> </td>
				<td><form:input path="pojo.email" id="email"/></td>
				<td><form:errors path="pojo.email" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="password">Password: </label> </td>
				<td><form:input path="pojo.password" id="password"/></td>
				<td><form:errors path="pojo.password" cssClass="error"/></td>
		    </tr>
				
			<tr>
				<td colspan="3">
					<input type="submit" value="Register"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>