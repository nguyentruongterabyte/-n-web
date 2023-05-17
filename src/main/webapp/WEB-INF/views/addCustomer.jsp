<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${pageType eq 'add'}">
		<title>Thêm khách hàng mới</title>
	</c:when>
	<c:otherwise>
		<title>Chỉnh sửa thông tin khách hàng</title>
	</c:otherwise>
</c:choose>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-customer-16.png">

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
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách kho hàng</a> <a
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
					<c:when test="${pageType eq 'add'}">
						Thêm khách hàng mới
					</c:when>
					<c:otherwise>
						Chỉnh sửa thông tin khách hàng
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
			<div class="col-md-8 col-md-offset-2">
				<form:form action="${pageContext.servletContext.contextPath}/khach-hang/xac-thuc.htm"
					modelAttribute="customer" method="post"
				>
					<div class="row mt-16">
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-6">
									<form:label path="id">Mã khách hàng</form:label>
								</div>
								<div class="col-md-6">
									<form:input path="id" class="form-control" readonly="true"/>
								</div>
							</div>
						</div>
						<div class="col-md-7 col-md-offset-1">
							<div class="row">
								<div class="col-md-2">
									<form:label path="name">Họ tên</form:label>
								</div>
								<div class="col-md-10">
									<form:input path="name" class="form-control"/>
									<form:errors path="name"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-8">
							<div class="row">
								<div class="col-md-3">
									<form:label path="phone">Số điện thoại</form:label>
								</div>
								<div class="col-md-9">
									<form:input path="phone" type="number" class="form-control"/>
									<form:errors path="phone"/>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-5">
									<form:label path="gender">Giới tính</form:label>
								</div>
								<div class="col-md-7">								
									<form:select path="gender" class="form-control">
										<form:option value="0">Nam</form:option>
										<form:option value="1">Nữ</form:option>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-8">
							<div class="row">
								<div class="col-md-3">
									<form:label path="email">Email</form:label>
								</div>
								<div class="col-md-9">
									<form:input path="email" class="form-control" type="email"/>
									<form:errors path="email"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-2">
							<form:label path="address">Địa chỉ</form:label>
						</div>
						<div class="col-md-8">
							<form:textarea path="address" cols="40" rows="3" class="form-control no-resize"/>
							<form:errors path="address"/>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-4">
									<form:label path="identifyNumber">CMND/CCCD</form:label>
								</div>
								<div class="col-md-8">
									<form:input path="identifyNumber" type="number" class="form-control"/>
									<form:errors path="identifyNumber"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-3 col-md-offset-1">
									<form:label path="groupOfCustomer">Loại khách hàng</form:label>
								</div>
								<div class="col-md-8">
									<form:select path="groupOfCustomer.id" class="form-control">
										<c:forEach var="groupOfCustomer" items="${groupOfCustomers}">
											<form:option value="${groupOfCustomer.id}">${groupOfCustomer.label}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-4">
									<form:label path="birthday">Ngày sinh</form:label>
								</div>
								<div class="col-md-8">
									<form:input path="birthday" class="form-control" type="date" value="${customer.birthday}"/>
									<form:errors path="birthday" />
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-16">
						<div class="col-md-2">
							<form:button class="btn btn-success btn-lg" style="width:100%;">Lưu</form:button>
						</div>
					</div>
				</form:form>
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