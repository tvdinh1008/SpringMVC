<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Danh sách nhân viên</title>
</head>
<body>
	<c:url var="createCustomerUrl" value="/quan-tri/customer/edit"></c:url>
	<a href="${createCustomerUrl}">Thêm mới nhân viên</a>
	<form action="<c:url value="/quan-tri/customer/list" />" id="formSubmit" method="get">
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
					<th>ID</th>
					<th>Name</th>
					<th>UserName</th>
					<th>Password</th>
					<th>Status</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${model.listResult}">
					<tr>
						<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"/></td>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.username}</td>
						<td>${item.password}</td>
						<td>${item.status}</td>
						<td>
							<c:url var="updateCustomerUrl" value="/quan-tri/customer/edit">
								<c:param name="id" value="${item.id}"/>
							</c:url>
							<a href="${updateCustomerUrl}">Update</a>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="hidden" name="currentPage" id="currentPage"/>
		<input type="hidden" name="maxPageItem" id="limit"/>
		
		<ul class="pagination" id="pagination"></ul>
	</form>

	<script type="text/javascript">
	
		var currentPage=${model.currentPage}; //page hiện tại đang đứng
		var totalPage=${model.totalPage}; //tổng số page
		var limit=2;//số item trong 1 page
		
	    $(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPage,
	            visiblePages: 5,
	            startPage:currentPage,
	            onPageClick: function (event, page) { //page: là page đc click
	                if(currentPage!=page){
	                	$('#currentPage').val(page);	                	
	                	$('#limit').val(limit);
	     
	                	$('#formSubmit').submit();
	                }
	            }
	        }).on('page', function (event, page) {
	            console.info(page + ' (from event listening)');
	        });
	    });
		
		
		
		
	</script>
</body>
</html>