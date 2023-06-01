<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khách hàng</title>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-customer-16.png">

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

.customer-list {
	margin-top: 12px;
	max-height: 80vh;
	overflow-y: scroll; 
	position: relative;
}

.customer-list__heading {
	position: sticky;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1;
}

.customer-list__table {
	position: relative;
}

.customer-list__table-heading {
	position: sticky;
	top: 38px;
	right: 0;
	background: #fff;

}

.customer-list__item {
	position: relative;
	cursor: pointer;
}

.customer-list__item:hover .customer-list__item-delete-btn {
	display: block;
}

.customer-list__item-delete-btn {
	position: absolute;
	top: 8px;
	right: 4px;
	display: none;	
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
			<div class="w3-center w3-padding-16">Danh sách khách hàng</div>
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
				<div class="panel panel-primary customer-list filterable">
					<div class="customer-list__heading panel-heading">
						<h3 class="panel-title">Khách hàng</h3>
						<div class="pull-right">
							<button class="btn btn-default btn-xs btn-filter">
								<span class="glyphicon glyphicon-filter"></span> Lọc
							</button>
						</div>
					</div>
					<table class="customer-list__table table table-hover">
						<thead class="customer-list__table-heading">
							<tr class="filters">
								<th><input type="text" class="form-control" placeholder="#"
									disabled></th>
								<th><input type="text" class="form-control"
									placeholder="Họ tên khách hàng" disabled></th>
								<th><input type="text" class="form-control"
									placeholder="Số điện thoại" disabled></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="c" items="${customers}">
								<tr <c:if test="${customer.id == c.id}">class="customer-list__item info"</c:if>class="customer-list__item"
									onclick="location.href='${pageContext.servletContext.contextPath}/khach-hang/chi-tiet.htm?id=${c.id}'"
								>
									<td>${c.id}</td>
									<td>${c.name}</td>
									<td>${c.phone}</td>
									<td>
										<button
											onclick="event.stopPropagation(); deleteCustomer(${c.id});"
											type="button" title="Delete"
											class="customer-list__item-delete-btn btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<form action="${pageContext.servletContext.contextPath}/khach-hang/them-moi.htm"
					 method="post">
					<button class="btn btn-success">Thêm khách hàng mới</button>
				</form>
			</div>
		</div>		
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	<script>
	function deleteCustomer(customerId) {
		var ok = confirm('Bạn có chắc muốn xóa khách hàng này?');
		if (ok) {
			location.href = "${pageContext.servletContext.contextPath}/khach-hang/xoa.htm?id=" + customerId;
		}
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