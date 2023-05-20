<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đơn xuất kho</title>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-document-16.png">

<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
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
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>

			<div class="w3-center w3-padding-16">
				<c:choose>
					<c:when test="${ inOutInventory.type == true}">
						Đơn xuất kho
					</c:when>
					<c:otherwise>
						Đơn nhập kho
					</c:otherwise>
				</c:choose>
			</div>
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
		<div class="row row-no-padding">
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-3">
						<img alt="Đơn xuất kho" src="${pageContext.servletContext.contextPath}/resource/images/inOutInventory/icons8-out-100.png">	
					</div>
					<div class="col-md-9">
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Mã</label>
							</div>
							<div class="col-md-8">
								<input class="form-control">	
							</div>
							
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Người lập</label>
							</div>
							<div class="col-md-8">
								<input class="form-control">	
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Ngày lập</label>
							</div>
							<div class="col-md-8">
								<input class="form-control">	
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Người chịu trách nhiệm</label>
							</div>
							<div class="col-md-8">
								<input class="form-control">	
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Phí vận chuyển</label>
							</div>
							<div class="col-md-8">
								<input class="form-control">	
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Kho</label>
							</div>
							<div class="col-md-8 mt-16">
								<input class="form-control">	
							</div>
						</div>
						<div class="row mt-16">
							<div class="col-md-4">
								<label>Kỳ</label>
							</div>
								<div class="col-md-8">
								<input class="form-control">	
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row mt-16">
					<div class="col-md-11 col-md-offset-1">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h6>Sức chứa kho hàng</h6>
							</div>
							<div class="panel-body" style="max-height: 50vh;">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Sản phẩm</th>
											<th>Sức chứa tối đa</th>
											<th>Số lượng hiện tại</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<div class="row row-no-padding">
			<div class="col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h6>Danh sách đơn hàng</h6>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Mã đơn hàng</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-5">
			
			</div>
			<div class="col-md-4">
				
			</div>
		</div>
	</div>
	<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
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