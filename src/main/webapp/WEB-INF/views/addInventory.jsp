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

.product-capability__option {
	position: relative !important;
}

.product-capability__option-btn {
	margin: 8px 0;
}

.product-capability__option-btn span {
	display: none;
}

.product-capability:hover .product-capability__option-btn span {
	display: block
}

.product-capability__option:hover .product-capability__option-btn-group {
 	display: block; 
}

.product-capability__option-btn-group {
	position: absolute;
    z-index: 1;
    left: 0;
    top: 70%; 
    display: none;
}

.product-capability__option-btn-delete {
	background: #fff;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    border: 1px solid #ccc;
    padding: 4px 10px;
    border-radius: 4px;
    cursor: pointer;
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
			<form:form action="${pageContext.servletContext.contextPath}/kho-hang/tao-moi/xac-thuc.htm" modelAttribute="inventory" method="post">
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
					${message}
					<div class="mt-12">
						<form:input path="id" class="form-control" readonly="true" />
					</div>
					<div class="mt-8">
						<form:input path="name" class="form-control" />
						<form:errors path="name" element="div"/>
					</div>
					<div class="mt-8">
						<form:textarea path="address" class="form-control no-resize"
							rows="3" cols="40" />
						<form:errors path="address" element="div"/>
					</div>
					<div class="mt-8">
						<form:input type="number" min="0" path="term" class="form-control" />
					</div>
					<div class="mt-8">
						<form:input type="number" min="0" path="rentPrice" class="form-control" />
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
											<option value="">---Chọn sản phẩm---</option>
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
		$('#product-select').on('change', function() {
			var productName = $(this).find(":selected").text();
			var productId = $(this).find(":selected").val();
			$(this).find('option[value=' + productId + ']').remove();
			var html = 
				`<tr class="product-capability row row-no-padding" data-product-id="` + productId + `" data-product-name="` + productName +`">` 
				+	`<td class="product-capability__option col-md-1">
						<div class="product-capability__option-btn">
							<span class="glyphicon glyphicon-triangle-right"></span>
						</div>
						<div class="product-capability__option-btn-group">
							<div class="product-capability__option-btn-delete" onclick="deleteProduct(` + productId + `)">Xóa</div>
						</div>
					</td>`
				+	`<td class="col-md-5">`
				+		`<select class="form-control"
						name="productsId[]">`
						
				+			`<option selected="selected" value="` 
							+ productId	+ `">`
							+ productName + `</option>`	
				+ 	`</td>`
				+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="maxCounts[]"></td>`
				+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="lasts[]"></td>`
				+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="currentCounts[]"></td>`
			+	`</tr>`;
			
			$('#inventory-capability-input').before(html);
			
		}); 
	</script>
	
	<script>
		function deleteProduct(productId) {
			var ok = confirm('Bạn có chắc muốn xóa sản phẩm này?');
			if (ok) {
				var deleteObject = $('tr[data-product-id="' + productId + '"]');
				var productName = deleteObject.attr('data-product-name');
				
				// Trả lại option cho thanh chọn sản phẩm
				var html = `<option value="` + productId + `">` + productName + `</option>`;
				var productSelect = $('#product-select');
				productSelect.append(html);
				
				// Xóa product đó ở danh sách sức chứa của kho hàng
				deleteObject.remove();
			}
		}
	</script>
</body>
</html>