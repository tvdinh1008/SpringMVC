<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tvdinh.util.SecurityUtils" %>

<h1>Đây là trang quản trị</h1>

<ul>
	<li>Trang chủ</li>
		<li><a href="#">Wellcome, <%=SecurityUtils.getPrincipal().getFullName() %></a></li>
		<li><a href="<c:url value='/thoat'/>">Thoát</a></li>
</ul>
