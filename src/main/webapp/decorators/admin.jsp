<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title><dec:title default="Trang chủ" /></title>
	
	<!-- Paging -->
	
	<link href="<c:url value='/template/admin/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript" src="<c:url value='/template/admin/jquery/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/admin/bootstrap/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
	
	<link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />" />
	 <!-- sweetalert -->
    <script src="<c:url value='/template/admin/sweetalert/sweetalert2.min.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/template/admin/sweetalert/sweetalert2.min.css' />" />
</head>
<body>
	<!-- header -->
    <%@ include file="/common/admin/header.jsp" %>
    <!-- header -->
    
	<!-- Page Content -->
 	<div class="container">
  		<dec:body/>
 	</div>
  
 	<!-- footer -->
	<%@ include file="/common/admin/footer.jsp" %>
	<!-- footer -->
</body>
</html>