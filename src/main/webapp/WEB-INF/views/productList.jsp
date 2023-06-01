<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-barcode-16.png">
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

.product-list {
	margin-top: 12px;
	max-height: 80vh;
	overflow-y: scroll;
	position: relative;
}

.product-list__heading {
	position: sticky;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1;
}

.product-list__table {
	position: relative;
}

.product-list__table-heading {
	position: sticky;
	top: 38px;
	right: 0;
	background: #fff;
}

.product-list__item {
	position: relative;
	cursor: pointer;
}

.product-list__item:hover .product-list__item-delete-btn {
	display: block;
}

.product-list__item-delete-btn {
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

			<div class="w3-center w3-padding-16">Danh sách sản phẩm</div>
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

			<!-- Danh sách sản phẩm -->
			<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<div class="panel panel-primary product-list filterable">
					<div class="product-list__heading panel-heading">
						<h3 class="panel-title">Sản phẩm</h3>
						<div class="pull-right">
							<button class="btn btn-default btn-xs btn-filter">
								<span class="glyphicon glyphicon-filter"></span> Lọc
							</button>
						</div>
					</div>
					<table class="product-list__table table table-hover">
						<thead class="product-list__table-heading">
							<tr class="filters">
								<th><input type="text" class="form-control" placeholder="#" disabled></th>
								<th><input type="text" class="form-control" placeholder="Tên sản phẩm" disabled></th>
								<th><input type="text" class="form-control" placeholder="Mã vạch" disabled></th>
								<th><input type="text" class="form-control" placeholder="Giá bán" disabled></th>
								<th><input type="text" class="form-control" placeholder="Đơn vị" disabled></th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="p" items="${products}">
								<tr <c:if test="${product.id == p.id}">class="product-list__item info"</c:if> class="product-list__item"
									onclick="location.href='${pageContext.servletContext.contextPath}/san-pham/chi-tiet.htm?id=${p.id}'"
								>
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td>${p.barCode}</td>
									<td><fmt:formatNumber value="${p.outPrice}" type="currency" currencyCode="VND" minFractionDigits="0"></fmt:formatNumber></td>
									<td>${p.unit}</td>
									<td>
										<button onclick="event.stopPropagation(); deleteProduct(${p.id});" type="button" title="Delete" class="product-list__item-delete-btn btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<form
					action="${pageContext.servletContext.contextPath}/san-pham/them-moi.htm"
					method="post">

					<button class="btn btn-success">Thêm mới sản
						phẩm</button>
				</form>
			</div>
		</div>
	</div>
	<script src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	<script>
		function deleteProduct(productId) {
			var ok = confirm('Bạn có chắc muốn xóa sản phẩm này?');
			if (ok) {
				location.href = "${pageContext.servletContext.contextPath}/san-pham/xoa.htm?id=" + productId;
			}
		}
		function w3_open() {
			  document.getElementById("mySidebar").style.display = "block";
			}
			 
			function w3_close() {
			  document.getElementById("mySidebar").style.display = "none";
			}
	</script>
	<script>
	window.addEventListener('load', () => {
	 	const targetElement = document.querySelector('.product-list__item.info');
	  	if (targetElement) {
	    	targetElement.scrollIntoView({
	      	behavior: 'smooth',
	      	block: 'center'
	    });
	  }
	});
	</script>
</body>
</html>