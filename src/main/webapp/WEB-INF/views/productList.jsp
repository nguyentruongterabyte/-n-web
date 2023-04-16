<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.product-list {
	margin-top: 12px;
	max-height: 85vh;
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
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="p" items="${products}">
								<tr <c:if test="${product.id == p.id}">class="product-list__item info"</c:if> class="product-list__item">
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td>${p.barCode}</td>
									<td>${p.outPrice}</td>
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
			</div>
		</div>
	</div>
	<script src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
	<script>
		function deleteProduct(productId) {
			var ok = confirm('Bạn có chắc muốn xóa sản phẩm này?');
			if (ok) {
				location.href = "${pageContext.servletContext.contextPath}/san-pham/xoa.htm?id=" + productId;
			}
		}
	</script>
</body>
</html>