<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tvdinh.util.SecurityUtils" %>


<ul>
	<li>Trang chủ</li>
	<security:authorize access = "isAnonymous()">
		<li><a href="<c:url value='/dang-nhap'/>">Đăng nhập</a></li>
		<li><a href="<c:url value='/dang-ky'/>">Đăng ký</a></li>
	</security:authorize>
	<security:authorize access = "isAuthenticated()">
		<li><a href="#">Wellcome, <%=SecurityUtils.getPrincipal().getFullName() %></a></li>
		<li><a href="<c:url value='/thoat'/>">Thoát</a></li>
	</security:authorize>
</ul>
