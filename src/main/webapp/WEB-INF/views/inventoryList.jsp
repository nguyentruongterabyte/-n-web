<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách kho hàng</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Quản lý</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/danh-sach.htm">Danh sách sản phẩm</a></li>
				<li class="active"><a href="${pageContext.servletContext.contextPath}/danh-sach-kho-hang.htm">Danh sách kho hàng</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-6" style="background-color: red; height: 100vh;">
				<table>
					<thead>
						<tr>
							<th><input type="text" class="form-control" placeholder="#" disabled></th>
							<th><input type="text" class="form-control" placeholder="Tên kho" disabled></th>
							<th><input type="text" class="form-control" placeholder="Địa chỉ" disabled></th>
							<th><input type="text" class="form-control" placeholder="Giá thuê" disabled></th>
						</tr>	
					</thead>
				</table>
			</div>
			<div class="col-md-6" style="background-color: blue; height: 100vh;"></div>
		</div>
	</div>
</body>
</html>