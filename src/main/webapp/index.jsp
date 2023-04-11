<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang chủ</title>
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
				<li><a
					href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm">Danh
						sách sản phẩm</a></li>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm">Danh
						sách kho hàng</a></li>
			</ul>
		</div>
	</nav>

</body>
</html>