<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới kho hàng</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/selectBox.css">
</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.inventory-capability {
	max-height: 85vh;
	overflow-y: scroll;
}

.inventory-capability__table {
	position: relative;
}

.inventory-capability__table-heading {
	position: sticky;
	top: 0;
	z-index: 2;
	background-color: #fff;
}
</style>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Quản lý</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/danh-sach-kho-hang.htm">Danh
						sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<form:form action="abc" modelAttribute="inventory" method="post">
				<div class="col-md-2">
					<div class="mt-12">
						<form:label path="id">Mã kho</form:label>
					</div>
					<div style="margin-top: 16px;">
						<form:label path="name">Tên kho</form:label>
					</div>
					<div style="margin-top: 12px;">
						<form:label path="address">Địa chỉ</form:label>
					</div>
					<div style="margin-top: 60px;">
						<form:label path="term">Kỳ</form:label>
					</div>
					<div style="margin-top: 18px;">
						<form:label path="rentPrice">Giá thuê</form:label>
					</div>
				</div>
				<div class="col-md-4">
					<div class="mt-12">
						<form:input path="id" class="form-control" readonly="true" />
					</div>
					<div class="mt-8">
						<form:input path="name" class="form-control" />
					</div>
					<div class="mt-8">
						<form:textarea path="address" class="form-control no-resize"
							rows="3" cols="40" />
					</div>
					<div class="mt-8">
						<form:input path="term" class="form-control" />
					</div>
					<div class="mt-8">
						<form:input path="rentPrice" class="form-control" />
					</div>
					<button class="btn btn-success btn-sm mt-16">Lưu</button>
				</div>
				<div class="col-md-6">
					<div class="inventory-capability">
						<table class="table table-hover inventory-capability__table">
							<thead class="inventory-capability__table-heading">
								<tr class="row">
									<th class="col-md-1"></th>
									<th class="col-md-5">Sản phẩm</th>
									<th class="col-md-2">Số lượng tối đa</th>
									<th class="col-md-2">Tồn kỳ trước</th>
									<th class="col-md-2">Số lượng hiện tại</th>
								</tr>
							</thead>
							<tbody id="inventory-capability-body">
								<tr id="inventory-capability-input" class="row row-no-padding">
									<td class="col-md-1"><span
										class="glyphicon glyphicon-asterisk"></span></td>
									<td class="col-md-5">
										<select class="form-control"
										name="product" id="product-select">
											<c:forEach var="p" items="${products}">
												<option value="${p.id}">${p.name}</option>
											</c:forEach>
										</select></td>
									<td class="col-md-2"></td>
									<td class="col-md-2"></td>
									<td class="col-md-2"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<script>
		$('#product-select').on('blur', function() {
			var productName = $(this).find(":selected").text();
			var productId = $(this).find(":selected").val();
			$(this).find('option[value=' + productId + ']').remove();
			var html = 
				`<tr class="row row-no-padding">` 
				+	`<td class="col-md-1"></td>`
				+	`<td class="col-md-5">`
				+		`<select class="form-control"
						name="productsId[]" id="product-select">`
						
				+		`<option selected="selected" value="` 
						+ productId	+ `">`
						+ productName + `</option>`	
				+		`<c:forEach var="p" items="${products}">
						<option value="${p.id}">${p.name}</option>
						</c:forEach>
						</select></td>`
				+ 	`</td>`
				+	`<td class="col-md-2"><input class="form-control" value="0" name="maxCounts[]"></td>`
				+	`<td class="col-md-2"><input class="form-control" value="0" name="lasts[]"></td>`
				+	`<td class="col-md-2"><input class="form-control" value="0" name="currentCounts[]"></td>`
			+	`</tr>`;
			
			$('#inventory-capability-input').before(html);
			
		}); 
	</script>
</body>
</html>