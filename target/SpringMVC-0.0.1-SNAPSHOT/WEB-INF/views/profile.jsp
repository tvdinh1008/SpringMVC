<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Profile Customer</h2>
  <table>
    <tr>
      <td>Id: </td>
      <td>${customer.id }</td>
    </tr>
    <tr>
      <td>Name: </td>
      <td>${customer.name }</td>
    </tr>
    <tr>
      <td>Username: </td>
      <td>${customer.username }</td>
    </tr>
    <tr>
      <td>Address: </td>
      <td>${customer.address }</td>
    </tr>
    <tr>
      <td>Email: </td>
      <td>${customer.email }</td>
    </tr>
    <tr>
      <td>password: </td>
      <td>${customer.password }</td>
    </tr>
    <tr>
      <td>Status: </td>
      <td>${customer.status }</td>
    </tr>
    <tr>
      <td>Roles: </td>
      <td>
        <c:if test="${not empty customer.roles}">
          <c:forEach var="role" items="${customer.roles}">${role.getName()} </c:forEach>
        </c:if>
      </td>
    </tr>
  </table>
	
</body>
</html>