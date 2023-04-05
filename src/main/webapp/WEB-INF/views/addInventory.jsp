<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới kho hàng</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/selectBox.css">
</head>
<style>
.inventory-capability {
	max-height: 85vh;
	overflow-y: scroll;
}

.inventory-capability__table {
	position: relative;
}

.inventory-capability__table-heading {
	position: sticky;
	top: 0;
	z-index: 2;
	background-color: #fff;
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
					href="${pageContext.servletContext.contextPath}/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/danh-sach-kho-hang.htm">Danh
						sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<form:form action="abc" modelAttribute="inventory" method="post">
				<div class="col-md-2">
					<div class="mt-12">
						<form:label path="id">Mã kho</form:label>
					</div>
					<div style="margin-top: 16px;">
						<form:label path="name">Tên kho</form:label>
					</div>
					<div style="margin-top: 12px;">
						<form:label path="address">Địa chỉ</form:label>
					</div>
					<div style="margin-top: 60px;">
						<form:label path="term">Kỳ</form:label>
					</div>
					<div style="margin-top: 18px;">
						<form:label path="rentPrice">Giá thuê</form:label>
					</div>
				</div>
				<div class="col-md-4">
					<div class="mt-12">
						<form:input path="id" class="form-control" readonly="true"/>
					</div>
					<div class="mt-8">
						<form:input path="name" class="form-control" />
					</div>
					<div class="mt-8">
						<form:textarea path="address" class="form-control no-resize"
							rows="3" cols="40"/>
					</div>
					<div class="mt-8">
						<form:input path="term" class="form-control"/>
					</div>
					<div class="mt-8">
						<form:input path="rentPrice" class="form-control"/>
					</div>
					<button class="btn btn-success btn-sm mt-16">Lưu</button>
				</div>
				<div class="col-md-6">
					<div class="inventory-capability">
						<table class="table table-hover inventory-capability__table">
							<thead class="inventory-capability__table-heading">
								<tr>
									<th></th>
									<th>Sản phẩm</th>
									<th>Số lượng tối đa</th>
									<th>Số lượng tồn kỳ trước</th>
									<th>Số lượng hiện tại</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td>
										<input type="checkbox" id="option-view-button">
									</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td>Sản phẩm 1</td> -->
<!-- 									<td>50</td> -->
<!-- 									<td>10</td> -->
<!-- 									<td>25</td> -->
<!-- 								</tr> -->
							</tbody>
						</table>
					</div>
				</div>
			</form:form>

		</div>
	</div>
</body>
</html>