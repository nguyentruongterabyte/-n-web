<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
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
					href="${pageContext.servletContext.contextPath}/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li><a href="#">Tạo hóa đơn</a></li>
				<li><a href="#">Tạo phiếu nhập</a></li>
				<li><a href="#">Tạo phiếu xuất</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		${message}
		<div class="row">
			<form:form modelAttribute="product"
				class="row col-md-8 col-md-offset-2"
				action="${pageContext.servletContext.contextPath}/san-pham/validate.htm"
				method="post">
				<div class="form-group col-md-2">
					<form:label path="id">Mã sản phẩm</form:label>
					<form:input path="id" class="form-control" />
					<form:errors path="id" />
				</div>
				<div class="form-group col-md-6">
					<form:label path="name">Tên sản phẩm</form:label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" />
				</div>
				<div class="form-group col-md-4">
					<form:label path="barCode">Mã vạch</form:label>
					<form:input path="barCode" class="form-control" />
					<form:errors path="barCode" />
				</div>
				<div class="form-group col-md-6">
					<form:label path="picture">Ảnh sản phẩm</form:label>
					<form:input type="file" path="picture" class="form-control" />
					<form:errors path="picture" />
				</div>
				<div class="form-group col-md-3">
					<form:label path="inPrice">Giá nhập</form:label>
					<form:input path="inPrice" class="form-control" />
					<form:errors path="inPrice" />
				</div>
				<div class="form-group col-md-3">
					<form:label path="outPrice">Giá bán</form:label>
					<form:input path="outPrice" class="form-control"/>
					<form:errors path="outPrice" />
				</div>

				<button>Chỉnh sửa</button>
			</form:form>

		</div>

	</div>
</body>
</html>