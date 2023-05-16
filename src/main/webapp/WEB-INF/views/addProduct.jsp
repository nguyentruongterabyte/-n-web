<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<c:choose>
	<c:when test="${pageType eq 'add'}">
		<title>Thêm mới sản phẩm</title>
	</c:when>
	<c:otherwise>
		<title>Chỉnh sửa sản phẩm</title>
	</c:otherwise>
</c:choose>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-barcode-16.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}	
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
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách kho hàng</a> <a
			href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách sản phẩm</a><a
			href="${pageContext.servletContext.contextPath}/nhan-vien/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhân viên</a><a
			href="${pageContext.servletContext.contextPath}/khach-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách khách hàng</a>
	</nav>
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
			<c:choose>
				<c:when test="${pageType eq 'add'}">
					<div class="w3-center w3-padding-16">Thêm mới sản phẩm</div>
				</c:when>
				<c:otherwise>
					<div class="w3-center w3-padding-16">Chỉnh sửa sản phẩm</div>
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
			<form
				action="${pageContext.servletContext.contextPath}/san-pham/xac-thuc.htm"
				method="post" enctype="multipart/form-data">
				<input name="pageType" value="${pageType}" style="display: none;">
				<div class="col-md-8">
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-id">Mã sản phẩm</label>
						</div>
						<div class="col-md-4">
							<input name="productId" id="product-id" class="form-control" value="${productId}" 
							readonly="readonly">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-name">Tên sản phẩm</label>
						</div>
						<div class="col-md-8">
							<input name="productName" id="product-name" class="form-control" value="${productName}">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="barcode">Mã vạch</label>
						</div>
						<div class="col-md-4">
							<input name="barcode" id="barcode" class="form-control" value="${barcode}">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-unit">Đơn vị</label>
						</div>
						<div class="col-md-4">
							<select name="productUnit" id="product-unit" class="form-control">
								<c:if test="${not empty productUnit}">
									<option selected>${productUnit}</option>
								</c:if>
								<option>Chai</option>
								<option>Thùng</option>
								<option>Két</option>
								<option>Lọ</option>
								<option>Lon</option>
								<option>Hộp</option>
							</select>
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="in-price">Giá nhập</label>
						</div>
						<div class="col-md-4">
							<input type="number" name="inPrice" id = "in-price" class="form-control" value = "${inPrice}">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="out-price">Giá bán</label>
						</div>
						<div class="col-md-4">
							<input type="number" name="outPrice" id = "out-price" class="form-control" value="${outPrice}">
						</div>
					</div>
					<div class="row" style="margin-top: 100px;">
						<button class="col-md-4 btn btn-info">Lưu</button>
					</div>
				</div>
				<div class="col-md-4">
					<div class="mt-12">
						<img id="product-img-preview" alt="choose-img" 
						<c:choose>
							<c:when test="${not empty photoPath}">
								src="${pageContext.servletContext.contextPath}${photoPath}"
							</c:when>
							<c:otherwise>
								src="${pageContext.servletContext.contextPath}/resource/images/product/choose.png"
							</c:otherwise>
						</c:choose>
						
							class="img-rounded" width="200">
					</div>
					<div class="mt-12">
						<input id="product-img" type="file"
							name="productImage">
						<input name="photoPath" value="${photoPath}" style="display: none;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	
	<script>
		let img = document.getElementById("product-img-preview");
		let input = document.getElementById("product-img");
		
		input.onchange = (e) => {
			if (input.files[0]) 
				img.src = URL.createObjectURL(input.files[0]);
		}
		function w3_open() {
			  document.getElementById("mySidebar").style.display = "block";
			}
			 
			function w3_close() {
			  document.getElementById("mySidebar").style.display = "none";
			}
	</script>
</body>
</html>