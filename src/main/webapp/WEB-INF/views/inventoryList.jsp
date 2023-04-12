<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách kho hàng</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">

</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.inventory-list {
	max-height: 78vh;
	overflow-y: scroll;
	position: relative;
}

.inventory-list__heading {
	position: sticky;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1;
}

.inventory-list__table {
	position: relative;
	max-height: 85vh;
}

.inventory-list__table-heading {
	position: sticky;
	top: 38px;
	right: 0;
	background: #fff;
}

.inventory-capability {
	max-height: 25vh;
	overflow-y: scroll;
}

.inventory-capability__list {
	position: relative;
}

.inventory-capability__list-heading {
	position: sticky;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1;
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
				<li><a
					href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm">Danh
						sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div id="toast">
			<c:choose>
				<c:when test="${messageType eq 'success'}">
					<!-- Toast success -->
					<div class="toast toast--success">
						<div class="toast__icon">
							<span class="glyphicon glyphicon-ok-sign"></span>
						</div>
						<div class="toast__body">
							<h3 class="toast__title">Thành công</h3>
							<p class="toast__msg">${message}</p>
						</div>
						<div class="toast__close">
							<i class="glyphicon glyphicon-remove"></i>
						</div>
					</div>
				</c:when>
				<c:when test="${messageType eq 'error' }">
					<div class="toast toast--error">
						<div class="toast__icon">
							<span class="glyphicon glyphicon-exclamation-sign"></span>
						</div>
						<div class="toast__body">
							<h3 class="toast__title">Lỗi</h3>
							<p class="toast__msg">${message}</p>
						</div>
						<div class="toast__close">
							<i class="glyphicon glyphicon-remove"></i>
						</div>
					</div>
				</c:when>
				
			</c:choose>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary mt-12 inventory-list filterable">
					<div class="inventory-list__heading panel-heading">
						<h3 class="panel-title">Kho hàng</h3>
						<div class="pull-right">
							<button class="btn btn-default btn-xs btn-filter">
								<span class="glyphicon glyphicon-filter"></span> Lọc
							</button>
						</div>
					</div>
					<table class="inventory-list__table table table-hover">
						<thead class="inventory-list__table-heading">
							<tr class="filters">
								<th><input type="text" class="form-control" placeholder="#"
									disabled></th>
								<th><input type="text" class="form-control"
									placeholder="Tên kho" disabled></th>
								<th><input type="text" class="form-control"
									placeholder="Địa chỉ" disabled></th>
								<th><input type="text" class="form-control"
									placeholder="Giá thuê" disabled></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${inventories}" var="i">
								<tr
									onclick="location.href='${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm?id=${i.id}'">
									<td>${i.id}</td>
									<td>${i.name}</td>
									<td>${i.address}</td>
									<td>${i.rentPrice}</td>
									<td>
										<form data-placement="top" data-toggle="tooltip"
											title="Option">
											<button class="btn btn-default btn-xs" data-toggle="modal"
												data-target="#option">
												<span class="glyphicon glyphicon-option-horizontal"></span>
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<form
					action="${pageContext.servletContext.contextPath}/kho-hang/tao-moi.htm"
					method="post">
					<button class="btn btn-success">
						<span class="glyphicon glyphicon-plus"></span> Tạo kho hàng mới
					</button>
				</form>
			</div>
			<div class="col-md-6">
				<h4>Thông tin</h4>
				<div class="row mt-12">
					<div class="col-md-3">
						<label for="inventory-id">Mã kho</label>
					</div>
					<div class="col-md-9">
						<input class="form-control" id="inventory-id" type="text"
							value="${inventory.id}" readonly>
					</div>
				</div>
				<div class="row mt-12">
					<div class="col-md-3">
						<label for="inventory-name">Tên kho</label>
					</div>
					<div class="col-md-9">
						<input class="form-control" id="inventory-name" type="text"
							value="${inventory.name}" readonly>
					</div>
				</div>
				<div class="row mt-12">
					<div class="col-md-3">
						<label for="inventory-address">Địa chỉ</label>
					</div>
					<div class="col-md-9">
						<textarea class="form-control no-resize" id="inventory-address"
							rows="3" cols="40" readonly>${inventory.address}</textarea>
					</div>
				</div>
				<div class="row mt-12">
					<div class="col-md-3">
						<label for="inventory-term">Kỳ</label>
					</div>
					<div class="col-md-3">
						<input class="form-control" id="inventory-term" type="text"
							value="${inventory.term}" readonly>
					</div>
				</div>
				<div class="row mt-12">
					<div class="col-md-3">
						<label for="inventory-rent-price">Giá thuê</label>
					</div>
					<div class="col-md-5">
						<input class="form-control" id="inventory-rent-price" type="text"
							value="${inventory.rentPrice}" readonly>
					</div>
				</div>


				<hr>

				<div class="inventory-capability">
					<table class="table table-hover inventory-capability__list">
						<thead class="inventory-capability__list">
							<tr>
								<th>Sản phẩm</th>
								<th>Số lượng tối đa</th>
								<th>Số lượng hiện tại</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inventoryCapability"
								items="${inventory.inventoryCapability}">
								<tr>
									<td>${inventoryCapability.embeddedId.product.name}</td>
									<td>${inventoryCapability.maxCount}</td>
									<td>${inventoryCapability.currentCount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<c:if test="${inventory.id != 0}">
					<div class="mt-12">
						<button type="button" class="btn btn-info" onclick="location.href='${pageContext.servletContext.contextPath}/kho-hang/chinh-sua.htm?id=${inventory.id}'">Chỉnh sửa</button>
					</div>	
				</c:if>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
</body>
</html>