<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo đơn từ</title>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-document-16.png">
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
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
			<div class="w3-center w3-padding-16">Tạo đơn từ mới</div>
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
			<div class="col-md-6 col-md-offset-3">
				<form:form 
					action="${pageContext.servletContext.contextPath}/don-tu/xac-thuc.htm"
					modelAttribute="document" 
					method="post">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3>Đơn từ</h3>
						</div>
						<div class="panel-body">

							<div class="row">
								<div class="col-md-2 col-md-offset-1">
									<form:label path="id">Mã đơn</form:label>
								</div>
								<div class="col-md-6">
									<form:input path="id" readonly="true" class="form-control" />
									<form:errors path="id"></form:errors>
								</div>
							</div>
							<div class="row mt-16">
								<div class="col-md-2 col-md-offset-1">
									<form:label path="staff">Người tạo</form:label>
								</div>
								<div class="col-md-8">
									<form:select path="staff.id" class="form-control">
										<c:forEach var="creator" items="${creators}">
											<form:option value="${creator.id}">${creator.name} - ${creator.func}</form:option>
										</c:forEach>
									</form:select>
									<form:errors path="staff"></form:errors>
								</div>
							</div>
							<div class="row mt-16">
								<div class="col-md-2 col-md-offset-1">
									<form:label path="type">Loại đơn</form:label>
								</div>
								<div class="col-md-6">
									<form:select path="type" class="form-control">
										<form:option value="customerorder">Bán hàng</form:option>
										<form:option value="ininventory">Nhập kho</form:option>
										<form:option value="outinventory">Xuất kho</form:option>
										<form:option value="vendororder">Nhập hàng</form:option>						
									</form:select>
									<form:errors path="type"></form:errors>
								</div>
							</div>
							<div class="row mt-16">
								<div class="col-md-2 col-md-offset-1">
									<form:label path="createDate">Ngày tạo</form:label>
								</div>
								<div class="col-md-6">
									<form:input path="createDate" type="datetime-local"
										class="form-control" />
									<form:errors path="createDate"></form:errors>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<form:button class="btn btn-info btn-lg" style="width: 100%;">Tạo</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
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
</body>
</html>