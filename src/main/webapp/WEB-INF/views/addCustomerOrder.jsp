<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đơn bán hàng</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-order-16.png">

<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
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
			class="w3-bar-item w3-button">Danh sách đơn từ</a><a
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách kho hàng</a><a
			href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách sản phẩm</a><a
			href="${pageContext.servletContext.contextPath}/nhan-vien/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhân viên</a><a
			href="${pageContext.servletContext.contextPath}/khach-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách khách hàng</a><a
			href="${pageContext.servletContext.contextPath}/nha-cung-cap/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhà cung cấp</a>

	</nav>

	<!-- Top menu -->
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>

			<div class="w3-center w3-padding-16">Đơn bán hàng</div>
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
					<div class="toast__close">
						<span class="glyphicon glyphicon-remove-circle"></span>
					</div>
				</div>
			</div>
		</c:if>
		<div class="row mt-16">
			<div class="col-md-4">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h6>Sản phẩm</h6>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">Kho chứa</div>
							<div class="col-md-10">
								<select id="inventory" name="inventoy" class="form-control">
									<option></option>
									<c:forEach var="i" items="${inventories}">
										<option value="${i.id}"
											<c:if test="${i.id == inventory.id}">
												selected
											</c:if>
										>${i.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-2">Mặt hàng</div>
							<div class="col-md-10">
								<select id="product-selected" name="product" class="form-control">
									<c:choose>
										<c:when test="${inventory != null}">
											<c:forEach var="ic"
												items="${inventory.inventoryCapabilities}">
												<option class="product-option" data-name="${ic.embeddedId.product.name}" data-price="${ic.embeddedId.product.outPrice}" value="${ic.embeddedId.product.id}">${ic.embeddedId.product.name} - còn ${ic.currentCount} ${ic.embeddedId.product.unit}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<option value="">Vui lòng chọn kho chứa</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-1">
				<button id="add-product-btn"  class="btn-info btn btn-lg"
				style="margin: 128px 0 0;"
				>Thêm>></button>
			</div>
			<div class="col-md-7">
				<div class="panel panel-default">
					<div class="panel-heading">Chi tiết hóa đơn</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Sản phẩm</th>
									<th>Giá</th>
									<th>Số lượng</th>
									<th>Thành tiền</th>
								</tr>
							</thead>
							<tbody id="product-list">
								
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
		</div>
		<div class="row"></div>
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#inventory').change(function() {
				window.location.href = "${pageContext.servletContext.contextPath}/don-ban-hang/chon-kho-hang.htm?id=" + $(this).val();
			})
			
			
			// Sự kiện nút thêm sản phẩm vào danh sách đươc click
			$('#add-product-btn').click(function() {
				
				// Lấy sản phẩm được chọn 
				var productSelected = $('#product-selected')
				// Lấy id sản phẩm
				var productSelectedId = productSelected.val();
				// Nếu id sản phẩm tồn tại (tức được chọn)
				if (productSelectedId) {
					var checkProductExist = $('#product-id-' + productSelectedId)
					// Kiểm tra thẻ tr đã có chưa
					// Nếu chưa có thì append vào thẻ tbody
					if (checkProductExist) {
						// Lấy thẻ tbody để chuẩn bị append thẻ tr vào
						var productList = $('#product-list')
						
						// Lấy data-name từ thẻ option bằng value = id của sản phẩm
						var productSelectedName = $('option.product-option').filter('[value="' + productSelectedId + '"]').data('name');
						
						// Lấy data-price từ thẻ option bằng value = id của sản phẩm trong thẻ option đó
						var productSelectedPrice = $('option.product-option').filter('[value="' + productSelectedId + '"]').data('price');
						
						var isReadonly = true;
						var newRow = $('<tr>').attr('id', 'product-id-' + productSelectedId).append(
							$('<td>').append(
								$('<input>').attr({
									'value': productSelectedName,
									type: 'text'
								}).addClass('form-control')
							),
							$('<td>').append(
								$('<input>').attr({
									value: productSelectedPrice,
									name: 'productsPrice[]'
								}).addClass('form-control')	
							),
							$('<td>'),
							$('<td>')
						);
						productList.append(newRow);
					}
				}
			})
		})
		
		
	</script>
</body>
</html>