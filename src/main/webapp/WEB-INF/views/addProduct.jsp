<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Chỉnh sửa thông tin</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Quản lý</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm">Danh
						sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
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
				<div class="col-md-8">
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-id">Mã sản phẩm</label>
						</div>
						<div class="col-md-4">
							<input name="productId" id="product-id" class="form-control">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-name">Tên sản phẩm</label>
						</div>
						<div class="col-md-8">
							<input name="productName" id="product-name" class="form-control">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="barcode">Mã vạch</label>
						</div>
						<div class="col-md-4">
							<input name="barcode" id="barcode" class="form-control">
						</div>
					</div>
					<div class="row mt-12">
						<div class="col-md-4">
							<label for="product-unit">Đơn vị</label>
						</div>
						<div class="col-md-4">
							<select name="productUnit" id="product-unit" class="form-control">
								<option>Chai</option>
								<option>Thùng</option>
								<option>Két</option>
								<option>Lọ</option>
								<option>Lon</option>
								<option>Hộp</option>
							</select>
						</div>
					</div>
				<div class="row">
					<button class="col-md-4 btn btn-info">Lưu</button>
				</div>
				</div>
				<div class="col-md-4">
					<div class="mt-12">
						<img id="product-img-preview" alt="choose-img"
							src="${pageContext.servletContext.contextPath}/resource/images/product/choose.png"
							class="img-rounded" width="100">
					</div>
					<div class="mt-12">
						<input id="product-img" type="file" class="form-control"
							name="productImage">
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		let img = document.getElementById("product-img-preview");
		let input = document.getElementById("product-img");
		
		input.onchange = (e) => {
			if (input.files[0]) 
				img.src = URL.createObjectURL(input.files[0]);
		}
	</script>
</body>
</html>