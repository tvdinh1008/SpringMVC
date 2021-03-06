<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Danh sách nhân viên</title>
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${not empty message}">
		<div class="alert alert-${alert}">
    		${message}
  		</div>
	</c:if>
	<form action="<c:url value="/quan-tri/customer/list" />" id="formSearch" method="get">
		Tìm kiếm: 
		<!-- Khi submit thì cái tìm kiếm vẫn hiện thị trên input tìm kiếm qua value -->
		<input type="text" name="pojo.username" value="${model.getPojo().getUsername()}"/>
		<button type="submit" id="btnSearch">Tìm kiếm</button>
	</form>
	
	<c:url var="createCustomerUrl" value="/quan-tri/customer/edit"></c:url>
	<a href="${createCustomerUrl}">Thêm mới nhân viên</a>
	
	<div class="dt-buttons btn-overlap btn-group">
	<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
		class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
			<span>
				<i class="fa fa-trash-o bigger-110 pink"></i>
			</span>
	</button>
	</div>
	<br>
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
					<th>Roles</th>
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
							<c:forEach var="role" items="${item.roles}" varStatus="status">
								<c:if test="${not status.last}">
									${role.getName()},  
								</c:if>
								<c:if test="${status.last}">
									${role.getName()}
								</c:if>		
							</c:forEach>
						</td>
						<td>
							<c:url var="updateCustomerUrl" value="/quan-tri/customer/edit">
								<c:param name="id" value="${item.id}"/>
							</c:url>
							<a href="${updateCustomerUrl}">Update</a>
							<!--Để phân biệt giữa thêm mới và sửa dựa vào url updateCustomerUrl truyền this để lấy thông tin đó -->
							<a sc-url="${updateCustomerUrl}" onclick="update(this)">Update - model</a>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="hidden" name="currentPage" id="currentPage"/>
		<input type="hidden" name="maxPageItem" id="limit"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<ul class="pagination" id="pagination"></ul>
		<p>Tổng có ${model.getTotalItem()} items</p>
	</form>
	
	<button type="button" class="btn btn-info btn-md" onclick="update(this)" id="myBtn">Thêm mới -model</button>
	<!-- Modal -->
	
  	<div class="modal fade" id="myModal" role="dialog"></div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btnSearch').click(function(){
				e.preventDefault();
				$('#formSearch').submit();
			});
		});
		
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
		
	    function warningBeforeDelete() {
			swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			  closeOnConfirm: false,
			  closeOnCancel: false
			}).then(function(isConfirm) {
			   if (isConfirm.value==true) {
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
					deleteNew(ids);
				  alert("Delete success!!");
			  } 
			});
		}
	    function deleteNew(ids) {
	    	var token=$('input[name="_csrf"]').attr('value');
	    	$.ajax({
	    		url:"<c:url value="/api/customer"/>",
				type:'DELETE',
				contentType:'application/json',
				data:JSON.stringify(ids),
				dataType:'json',
				headers: {
                    'X-CSRF-Token': token 
            	},
				success: function (result) {
	                window.location.href = "<c:url value="/quan-tri/customer/list"/>?currentPage=1&maxPageItem=2&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "<c:url value="/quan-tri/customer/list"/>?currentPage="+currentPage+"&maxPageItem="+limit+"&message=error_system";
	            }
	    	});
	    }
	    
	    function update(btn){
	    	var editUrl=$(btn).attr('sc-url');//TH update
			if(typeof editUrl == 'undefined'){
				//TH thêm mới
				editUrl='${createCustomerUrl}';
			}
	    	$("#myModal").load(editUrl,'',function(){
	    		 $("#myModal").modal("toggle");
	    	});
	 
	    }
	
	</script>
	
</body>
</html>