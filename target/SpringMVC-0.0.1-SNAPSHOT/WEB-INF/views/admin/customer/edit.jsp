<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Chỉnh sửa nhân viên</title>
</head>
<body>
	
	<c:if test="${not empty message}">
		<div class="alert alert-${alert}">
    		${message}
  		</div>
	</c:if>
	
	<form:form id="formSubmit" modelAttribute="model">
		<form:hidden path="pojo.id" id="customerId"/>
		name
		<form:input path="pojo.name"/>
		</br>
		username
		<form:input path="pojo.username"/>
		</br>
		password
		<form:input path="pojo.password"/>
		</br>
		address
		<form:input path="pojo.address"/>
		</br>
		email
		<form:input path="pojo.email"/>
		</br>
		status
		<!-- <select name="pojo.status">
			<option value="">Chọn trạng thái</option>
			<option value="1">Bình thường</option>
			<option value="2">Bị chặn</option>
		</select> -->
		<form:select path="pojo.status">
			<form:option value="" label="Chọn trạng thái"/>
			<form:options items="${statuses}"/>
		</form:select>
		</br>
		roles
		<%-- <select name="roleCode">
			<option value="">Chọn phân quyền</option>
			<c:forEach var="entry" items="${roles}">
				<option value="${entry.key}">${entry.value}</option>
				<option value="${item.code}">${item.name}</option>
				<option value="${item.code}" <c:if test="${item.code==pojo.getRole.get(0).getCode()}">selected="selected"</c:if>>${item.name}</option>
			</c:forEach>
		</select>
		 --%>
		
		<%--roleCode khi có giá trị thì nó sẽ tự động tich --%>
		<form:checkboxes items="${roles}" path="roleCode"/>
		
		<%-- <form:select path="roles">
			<form:option value="" label="Chọn thể loại"/>
			<form:options items="${roles}"/>
		</form:select> --%>
		</br>
		<c:if test="${not empty model.pojo.id}">
			<button type="button" id="btnAddOrUpdateCustomer">Cập nhật nhân viên</button>
		</c:if>
		<c:if test="${empty model.pojo.id }">
			<button type="button" id="btnAddOrUpdateCustomer">Thêm mới nhân viên</button>
		</c:if>
	</form:form>
	
	<script type="text/javascript">
		$('#btnAddOrUpdateCustomer').click(function (e) {
		    e.preventDefault();
			
		    //Khai báo kiểu đối tượng
		    var data = {};		    
		    //The serializeArray() method creates an array of objects (name and value) by serializing form values.
		    var formData=$('#formSubmit').serializeArray();
		    data["roleCode"]=[];
		    $.each(formData, function(i, field){
		    	if(field.name=="roleCode"){
		    		data[""+field.name+""].push(field.value);
		    	}else {
		    		data[""+field.name+""] = field.value;
		    	}
		    });
		    //convert object sang JSON
		    var x=JSON.stringify(data);
		    var id=$('#customerId').val();
		    if(id == ""){
		    	addCustomer(data);
		    } else{
		    	updateCustomer(data);
		    }
		});
		
		function addCustomer(data){
			$.ajax({
				url:"<c:url value="/api/customer"/>",
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType:'json',
				//result chính là model trả về
				success:function(result){
					//Nếu thành công thì nó sẽ redirect tới url này
					window.location.href="<c:url value="/quan-tri/customer/edit"/>?id="+result.pojo.id+"&message=insert_success";
				},
				error:function(result){
					//Nếu thất bại
					window.location.href="<c:url value="/quan-tri/customer/edit"/>?message=error_system";
				}
				
			});
		}
		function updateCustomer(data){
			$.ajax({
				url:'<c:url value="/api/customer"/>',
				type:'PUT',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType:'json',
				//result chính là model trả về
				success:function(result){ 
					//Nếu thành công thì nó sẽ redirect tới url này
					window.location.href="<c:url value="/quan-tri/customer/edit"/>?id="+result.pojo.id+"&message=update_success";
				},
				error:function(result){
					//Nếu thất bại
					window.location.href="<c:url value="/quan-tri/customer/edit"/>?message=error_system";
				}
				
			});
		}
		
		
	</script>	
	
</body>
</html>