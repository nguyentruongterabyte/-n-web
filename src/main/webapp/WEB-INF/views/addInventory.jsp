<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${pageType eq 'edit'}">
		<title>Chỉnh sửa kho hàng</title>
	</c:when>
	<c:when test="${pageType eq 'add'}">
		<title>Thêm mới kho hàng</title>
	</c:when>
	<c:otherwise>
		<title>Thêm mới kho hàng</title>
	</c:otherwise>
</c:choose>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-inventory-16.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">

</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}	
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

.product-capability__option:hover .product-capability__option-btn-group
	{
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
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	border: 1px solid #ccc;
	padding: 4px 10px;
	border-radius: 4px;
	cursor: pointer;
}
</style>
<body>
	<nav
		class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
		style="display: none; z-index: 2; width: 40%; min-width: 300px"
		id="mySidebar">
		<a href="javascript:void(0)" onclick="w3_close()"
			class="w3-bar-item w3-button">Close Menu</a> <a
			href="${pageContext.servletContext.contextPath}/"
			class="w3-bar-item w3-button">Trang chủ</a> <a
			href="${pageContext.servletContext.contextPath}/don-tu/danh-sach.htm"
			class="w3-bar-item w3-button">Đơn từ</a><a
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Kho hàng</a><a
			href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm"
			class="w3-bar-item w3-button">Sản phẩm</a><a
			href="${pageContext.servletContext.contextPath}/nhan-vien/danh-sach.htm"
			class="w3-bar-item w3-button">Nhân viên</a><a
			href="${pageContext.servletContext.contextPath}/khach-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Khách hàng</a><a
			href="${pageContext.servletContext.contextPath}/nha-cung-cap/danh-sach.htm"
			class="w3-bar-item w3-button">Nhà cung cấp</a>
			
	</nav>
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
			<c:choose>
				<c:when test="${pageType eq 'add'}">
					<div class="w3-center w3-padding-16">Thêm mới kho hàng</div>
				</c:when>
				<c:otherwise>
					<div class="w3-center w3-padding-16">Chỉnh sửa kho hàng</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="container">
		<c:if test="${message.type ne null}">
			<div id="toast">
				<div class="toast toast--${message.type}">
					<div class="toast__icon">
						<c:choose>		
							<c:when test="${message.type eq 'success'}">
								<span class="glyphicon glyphicon-ok-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'error'}">
								<span class="glyphicon glyphicon-exclamation-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'info'}">
								<span class="glyphicon glyphicon-info-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'warning'}">
								<span class="glyphicon glyphicon-warning-sign"></span>
							</c:when>
						</c:choose>
					</div>
					<div class="toast__body">
						<h3 class="toast__title"></h3>
						<p class="toast__msg">${message.content}</p> 
					</div>
				</div>
			</div>
		</c:if>
		<div class="row">
			<form:form
				action="${pageContext.servletContext.contextPath}/kho-hang/xac-thuc.htm"
				modelAttribute="inventory" method="post">
				<input name="pageType" value="${pageType}" style="display: none;">
				<div class="col-md-6">
					<div class="row mt-12">
						<div class="col-md-4">
							<form:label path="id">Mã kho</form:label>
						</div>
						<div class="col-md-8">
							<form:input path="id" class="form-control" readonly="true"/>
							<form:errors path="id" element="div" />
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<form:label path="name">Tên kho</form:label>
						</div>
						<div class="col-md-8">
							<form:input path="name" class="form-control" />
							<form:errors path="name" element="div" />
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<form:label path="address">Địa chỉ</form:label>
						</div>
						<div class="col-md-8">
							<form:textarea path="address" cols="40" rows="3" class="form-control no-resize"/>
							<form:errors path="address"/>
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<form:label path="term">Kỳ</form:label>
						</div>
						<div class="col-md-3">
							<form:input type="number" min="0" path="term"
								class="form-control" />
							<form:errors path="term" element="div" />
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<form:label path="rentPrice">Giá thuê</form:label>
						</div>
						<div class="col-md-5">
												
							<form:input type="number" min="0" step="100" path="rentPrice"
								class="form-control" />
							<form:errors path="term" element="div" />
						</div>
					</div>
					<div class="row row-no-padding">
						<button class="col-md-offset-10 col-md-2 mt-12 btn btn-success">Lưu</button>
					</div>
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
								<c:forEach var="inventoryCapability" items="${inventory.inventoryCapabilities}">
									<tr class="product-capability row row-no-padding" data-product-id="${inventoryCapability.embeddedId.product.id}" data-product-name="${inventoryCapability.embeddedId.product.name}">
										<td class="product-capability__option col-md-1">
											<div class="product-capability__option-btn">
												<span class="glyphicon glyphicon-triangle-right"></span>
											</div>
											<div class="product-capability__option-btn-group">
												<div class="product-capability__option-btn-delete" onclick="deleteProduct(${inventoryCapability.embeddedId.product.id})">Xóa</div>
											</div>
										</td>	
										<td class="col-md-5">
											<select class="form-control" name="productsId[]">
												<option selected="selected" value="${inventoryCapability.embeddedId.product.id}">${inventoryCapability.embeddedId.product.name}</option>
											</select>
										</td>	
										<td class="col-md-2"><input type="number" min="0" class="form-control" name="maxCounts[]" value="${inventoryCapability.maxCount}"></td>										
										<td class="col-md-2"><input type="number" min="0" class="form-control" name="lasts[]" value="${inventoryCapability.last}"></td>										
										<td class="col-md-2"><input type="number" min="0" class="form-control" name="currentCounts[]" value="${inventoryCapability.currentCount}"></td>										
									</tr>
								</c:forEach>
								<tr id="inventory-capability-input" class="row row-no-padding">
									<td class="col-md-1"><span
										class="glyphicon glyphicon-asterisk"></span></td>
									<td class="col-md-5"><select class="form-control"
										name="product" id="product-select">
											<option value="">Chọn sản phẩm</option>
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
	<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	
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
	<script>
	function w3_open() {
		  document.getElementById("mySidebar").style.display = "block";
		}
		 
		function w3_close() {
		  document.getElementById("mySidebar").style.display = "none";
		}
	</script>
</body>
</html>