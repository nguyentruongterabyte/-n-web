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
</style>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Quản lý</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li class="active"><a href="${pageContext.servletContext.contextPath}/danh-sach.htm">Danh sách sản phẩm</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/danh-sach-kho-hang.htm">Danh sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
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
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="p" items="${products}">
								<tr>
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td>${p.barCode}</td>
									<td>${p.outPrice}</td>
									<td>
										<form
											action="${pageContext.servletContext.contextPath}/san-pham/${p.id}.htm?lnkEdit"
											method="post" data-placement="top" data-toggle="tooltip"
											title="Sửa">
											<button class="btn btn-primary btn-xs" data-title="Edit"
												data-toggle="modal" data-target="#edit">
												<span class="glyphicon glyphicon-pencil"> </span>
											</button>
										</form>
									</td>
									<td>
										<form data-placement="top" data-toggle="tooltip"
											title="Delete">
											<button class="btn btn-danger btn-xs" data-title="Delete"
												data-toggle="modal" data-target="#delete">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										</form>
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
</body>
</html>