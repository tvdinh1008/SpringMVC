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
	
	<p>Click this paragraph.</p>
	
	<script>
	$(document).ready(function(){
	  $("p").on("click",aaaa());
	});
	function aaaa(data){
		alert("The paragraph was clicked.");
	}
	</script>
	
	<c:if test="${not empty message}">
		<div class="alert alert-${alert}">
    		${message}
  		</div>
	</c:if>
		<div class="modal-dialog">
	     	<!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		        	<h4 class="modal-title">Modal Methods</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          
		        </div>
		        <div class="modal-body">
		          
	<form:form id="formSubmit" modelAttribute="model">
		<form:hidden path="pojo.id" id="customerId"/>
		name
		<form:input path="pojo.name"/>
		</br>
		</br>
		username
		<c:if test="${not empty model.pojo.id}">
			<form:input path="pojo.username" disabled="true"/>
		</c:if>
		<c:if test="${empty model.pojo.id }">
			<form:input path="pojo.username"/>
		</c:if>
		</br>
		</br>
		password
		<form:input path="pojo.password"/>
		</br>
		</br>
		address
		<form:input path="pojo.address"/>
		</br>
		</br>
		email
		<form:input path="pojo.email"/>
		</br>
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
	</div>
	<div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
   		 </div>   
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btnAddOrUpdateCustomer').click(function (e) {
			    e.preventDefault();
				
			    //Khai báo kiểu đối tượng
			    var object = {};	
			    var data={};
			    //The serializeArray() method creates an array of objects (name and value) by serializing form values.
			    var formData=$('#formSubmit').serializeArray();
			    data["roleCode"]=[];
			    $.each(formData, function(i, field){
			    	if(field.name!="roleCode"){
			    		var name=field.name.replace("pojo.","");
			    		object[""+name+""] = field.value;
			    	}
			    });
			    object["username"]=$('input[name="pojo.username"]').val();
			    $.each($("input[type='checkbox']:checked"), function(){
			    	data["roleCode"].push($(this).val());
	            });
			    data["pojo"]=object;
			    //convert object sang JSON
			    var x=JSON.stringify(data);
			    var id=$('#customerId').val();
			    var token=$('input[name="_csrf"]').attr('value');
			    if(id == ""){
			    	addCustomer(data,token);
			    } else{
			    	updateCustomer(data,token);
			    }
			});
			
		});
		function addCustomer(data,token){
			$.ajax({
				url:"<c:url value="/api/customer"/>",
				type:'POST',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType:'json',
				headers: {
	                    'X-CSRF-Token': token 
	            },
				//result chính là model trả về
				success:function(result){
					//Nếu thành công thì nó sẽ redirect tới url này
					if(jQuery.isEmptyObject(result.pojo)){
						window.location.href="<c:url value="/quan-tri/customer/edit"/>?message=error_system";
					}else{
						window.location.href="<c:url value="/quan-tri/customer/edit"/>?id="+result.pojo.id+"&message=insert_success";
					}
				},
				error:function(result){
					//Nếu thất bại
					window.location.href="<c:url value="/quan-tri/customer/edit"/>?message=error_system";
				}
				
			});
		}
		function updateCustomer(data,token){
			$.ajax({
				url:'<c:url value="/api/customer"/>',
				type:'PUT',
				contentType:'application/json',
				data:JSON.stringify(data),
				dataType:'json',
				headers: {
                    'X-CSRF-Token': token 
            	},
				//result chính là model trả về
				success:function(result){ 
					//Nếu thành công thì nó sẽ redirect tới url này
					if(jQuery.isEmptyObject(result.pojo)){
						window.location.href="<c:url value="/quan-tri/customer/edit"/>?id="+data.pojo.id+"&message=error_system";
					}else{
						window.location.href="<c:url value="/quan-tri/customer/edit"/>?id="+result.pojo.id+"&message=update_success";
					}
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