<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-barcode-16.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}

#product-detail	{
	animation: slideInRight ease 1s forwards;
}

.product-detail__img {
	width: 80%;
}

* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
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
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách kho hàng</a> <a
			href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách sản phẩm</a><a
			href="${pageContext.servletContext.contextPath}/nhan-vien/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhân viên</a><a
			href="${pageContext.servletContext.contextPath}/khach-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách khách hàng</a>
	</nav>
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>

			<div class="w3-center w3-padding-16">Chi tiết sản phẩm</div>
		</div>
	</div>
	<div class="container" >
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
		<div class="row" id ="product-detail">
			<div class = "col-md-6">
				<img alt="san-pham-${product.id}" class="product-detail__img"
					<c:choose>
						<c:when test="${not empty product.picture}">
							src="${pageContext.servletContext.contextPath}${product.picture}"
						</c:when>
						<c:otherwise>
							src="${pageContext.servletContext.contextPath}/resource/images/product/choose.png"
						</c:otherwise>
					</c:choose>
				>
			</div>
			<div class= "col-md-6">
				<form action="${pageContext.servletContext.contextPath}/san-pham/chinh-sua.htm?id=${product.id}" method="post">
					<div class="row">
						<div class="col-25">
							<label for="productName">
								Tên sản phẩm
							</label>
						</div>
						<div class="col-75">
							<input type = "text" id="productName" name="productName" value="${product.name}" disabled="disabled">
						</div>
					</div>
					<div class="row">
						<div class="col-25">
							<label for="barCode">
								Mã vạch
							</label>
						</div>
						<div class="col-75">
							<input type = "text" id="barCode" name="barCode" value="${product.barCode}" disabled="disabled">
						</div>
					</div>
					<div class="row">
						<div class="col-25">
							<label for="unit">
								Đơn vị
							</label>
						</div>
						<div class="col-75">
							<input type = "text" id="unit" name="unit" value="${product.unit}" disabled="disabled">
						</div>
					</div>
					<div class="row">
						<div class="col-25">
							<label for="inPrice">
								Giá nhập
							</label>
						</div>
						<div class="col-75">
							<input type = "text" id="inPrice" name="inPrice" value="${product.inPrice}" disabled="disabled">
						</div>
					</div>
					<div class="row">
						<div class="col-25">
							<label for="outPrice">
								Giá bán
							</label>
						</div>
						<div class="col-75">
							<input type = "text" id="outPrice" name="outPrice" value="${product.outPrice}" disabled="disabled">
						</div>
					</div>
					<div class="row">
						<input type="submit" value="Chỉnh sửa">
					</div>
				</form>
				<div class="row">
					<a style = "border: 1px solid #000; border-radius: 5px; text-decoration: none;"
					class="col-md-1 glyphicon glyphicon-arrow-left"
					href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm?id=${product.id}">
						
					</a>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>

<script>

function w3_open() {
	  document.getElementById("mySidebar").style.display = "block";
	}
	 
	function w3_close() {
	  document.getElementById("mySidebar").style.display = "none";
	}
</script>
</html>