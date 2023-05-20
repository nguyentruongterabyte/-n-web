<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Trang chủ</title>
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-cola-16.png">
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
}
</style>
</head>
<body>

	<!-- Sidebar (hidden by default) -->
	<nav
		class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
		style="display: none; z-index: 2; width: 40%; min-width: 300px"
		id="mySidebar">
		<a href="javascript:void(0)" onclick="w3_close()"
			class="w3-bar-item w3-button">Close Menu</a> <a
			href="${pageContext.servletContext.contextPath}/"
			class="w3-bar-item w3-button">Trang chủ</a> <a
			href="${pageContext.servletContext.contextPath}/don-tu/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách đơn từ</a><a
			href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách kho hàng</a><a
			href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách sản phẩm</a><a
			href="${pageContext.servletContext.contextPath}/nhan-vien/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhân viên</a><a
			href="${pageContext.servletContext.contextPath}/khach-hang/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách khách hàng</a><a
			href="${pageContext.servletContext.contextPath}/nha-cung-cap/danh-sach.htm"
			class="w3-bar-item w3-button">Danh sách nhà cung cấp</a>
			
	</nav>

	<!-- Top menu -->
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
			<div class="w3-right w3-padding-16">Mail</div>
			<div class="w3-center w3-padding-16">Quản lý bán nước</div>
		</div>
	</div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main w3-content w3-padding" onclick="w3_close()"
		style="max-width: 1200px; margin-top: 100px">

		<!-- First Photo Grid-->
		<div class="w3-row-padding w3-padding-16 w3-center" id="food">
		
			<c:forEach var="p" items="${products}">
				<div class="w3-quarter">
					<img alt="${p.name}" src="${pageContext.servletContext.contextPath}${p.picture}" width="200">
					<h3>${p.name}</h3>
					<p></p>
				</div>
			</c:forEach>
			
		</div>

		
		<!-- Pagination -->
		<div class="w3-center w3-padding-32">
			<div class="w3-bar">
				<c:if test="${activeProductPage != 1}">
					<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>			
				</c:if>
				<c:forEach begin="1" end="${pageProductTotal}" var="pageNumber">
					
					<c:choose>
						<c:when test="${pageNumber == activeProductPage}">
							<a
							href="${pageContext.servletContext.contextPath}/trang-chu.htm?productPage=${pageNumber}"
							class="w3-bar-item w3-black w3-button">${pageNumber}</a>
						</c:when>
						<c:otherwise>
							<a
							href="${pageContext.servletContext.contextPath}/trang-chu.htm?productPage=${pageNumber}"
							class="w3-bar-item w3-button w3-hover-black">${pageNumber}</a>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${activeProductPage != pageProductTotal}">
					 <a
						href="#"
						class="w3-bar-item w3-button w3-hover-black">»</a>	
				</c:if>
			</div>
		</div>

		<hr id="about">

		<!-- About Section -->
		<div class="w3-container w3-padding-32 w3-center">
			<h3>About Me, The Food Man</h3>
			<br> <img src="/w3images/chef.jpg" alt="Me" class="w3-image"
				style="display: block; margin: auto" width="800" height="533">
			<div class="w3-padding-32">
				<h4>
					<b>I am Who I Am!</b>
				</h4>
				<h6>
					<i>With Passion For Real, Good Food</i>
				</h6>
				<p>Just me, myself and I, exploring the universe of unknownment.
					I have a heart of love and an interest of lorem ipsum and mauris
					neque quam blog. I want to share my world with you. Praesent
					tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta
					lectus vitae, ultricies congue gravida diam non fringilla. Praesent
					tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta
					lectus vitae, ultricies congue gravida diam non fringilla.</p>
			</div>
		</div>
		<hr>

		<!-- Footer -->
		<footer class="w3-row-padding w3-padding-32">
			<div class="w3-third">
				<h3>FOOTER</h3>
				<p>Praesent tincidunt sed tellus ut rutrum. Sed vitae justo
					condimentum, porta lectus vitae, ultricies congue gravida diam non
					fringilla.</p>
				<p>
					Powered by <a href="https://www.w3schools.com/w3css/default.asp"
						target="_blank">w3.css</a>
				</p>
			</div>

			<div class="w3-third">
				<h3>BLOG POSTS</h3>
				<ul class="w3-ul w3-hoverable">
					<li class="w3-padding-16"><img src="/w3images/workshop.jpg"
						class="w3-left w3-margin-right" style="width: 50px"> <span
						class="w3-large">Lorem</span><br> <span>Sed mattis
							nunc</span></li>
					<li class="w3-padding-16"><img src="/w3images/gondol.jpg"
						class="w3-left w3-margin-right" style="width: 50px"> <span
						class="w3-large">Ipsum</span><br> <span>Praes tinci
							sed</span></li>
				</ul>
			</div>

			<div class="w3-third w3-serif">
				<h3>POPULAR TAGS</h3>
				<p>
					<span class="w3-tag w3-black w3-margin-bottom">Travel</span> <span
						class="w3-tag w3-dark-grey w3-small w3-margin-bottom">New
						York</span> <span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Dinner</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Salmon</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">France</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Drinks</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Ideas</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Flavors</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Cuisine</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Chicken</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Dressing</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Fried</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Fish</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Duck</span>
				</p>
			</div>
		</footer>

		<!-- End page content -->
	</div>

	<script>
		// Script to open and close sidebar
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script>

</body>
</html>