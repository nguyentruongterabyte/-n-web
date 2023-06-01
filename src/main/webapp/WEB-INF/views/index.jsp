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

	<!-- Top menu -->
	<div class="w3-top">
		
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
			<div class="w3-right w3-padding-16"><a href="${pageContext.servletContext.contextPath}/dang-xuat.htm">Log out <span class="glyphicon glyphicon-log-out"></span></a></div>
			<div class="w3-center w3-padding-16">Quản lý bán nước</div>
		</div>
	</div>

	<!-- !PAGE CONTENT! -->
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
	
	<div class="w3-main w3-content w3-padding" onclick="w3_close()"
		style="max-width: 1200px; margin-top: 100px">

		<!-- First Photo Grid-->
		<div class="w3-row-padding w3-padding-16 w3-center" id="food">
		
			<c:forEach var="p" items="${products}">
				<div class="w3-quarter">
					<img class="img img-rounded" alt="${p.name}" src="${pageContext.servletContext.contextPath}${p.picture}" width="200">
					<h3>${p.name}</h3>
					<p></p>
				</div>
			</c:forEach>
			
		</div>

		
		<!-- Pagination -->
		<div class="w3-center w3-padding-32">
			<div class="w3-bar">
				<c:if test="${activeProductPage != 1}">
					<a href="${pageContext.servletContext.contextPath}/trang-chu.htm?productPage=${activeProductPage - 1}" class="w3-bar-item w3-button w3-hover-black">«</a>			
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
						href="${pageContext.servletContext.contextPath}/trang-chu.htm?productPage=${activeProductPage + 1}"
						class="w3-bar-item w3-button w3-hover-black">»</a>	
				</c:if>
			</div>
		</div>

		<hr id="about">

		<!-- About Section -->
		<div class="w3-container w3-padding-32 w3-center">
			<h3>About Drinking Sales Agent</h3>
			<br> <img src="${pageContext.servletContext.contextPath}/resource/images/home/cua-hang-tien-loi-1-0942.jpg" alt="Me" class="w3-image img img-rounded"
				style="display: block; margin: auto" width="800" height="533">
			<div class="w3-padding-32">
				<h4>
					<b>Prestige, quality, affordable!</b>
				</h4>
				<h6>
					<i>With Passion For Real, Good Drinking</i>
				</h6>
				<p>Welcome to FreshDrop, your premier destination for high-quality bottled water solutions. As a leading distributor of bottled water, we take pride in providing refreshing and pure drinking water to satisfy the thirst of our valued customers. With a commitment to excellence and customer satisfaction, we have established ourselves as a trusted agency in the industry.

At FreshDrop, we understand the importance of clean and safe drinking water for your well-being. That's why we source our water from pristine natural springs, ensuring its exceptional quality. Our advanced purification processes guarantee that each drop of water meets the highest standards of purity, making it the perfect choice for hydration, whether at home, in the office, or on the go.

As a reliable supplier, we offer a diverse range of bottled water options to cater to your specific needs. From conveniently sized single-serve bottles to larger containers for your home or office, we have a comprehensive selection to suit every requirement. Our wide variety of bottle designs and packaging options ensure that you can enjoy our refreshing water with style and convenience.

FreshDrop is dedicated to sustainability and environmental responsibility. We prioritize the use of eco-friendly materials in our packaging and actively seek ways to reduce our carbon footprint throughout our operations. By choosing FreshDrop, you not only benefit from premium-quality water but also contribute to a greener future.

We take great pride in our exceptional customer service. Our knowledgeable and friendly team is always ready to assist you with any inquiries or special requests. Whether you need guidance in selecting the right products or require timely delivery, we are committed to providing you with a seamless and enjoyable experience.

So why settle for anything less than the best when it comes to your hydration needs? Choose FreshDrop as your trusted bottled water supplier and experience the refreshing difference that our premium water brings. Discover a world of pure hydration and exceptional service with FreshDrop today.

Contact us now to learn more about our offerings or to place an order. Quench your thirst with FreshDrop – where pure water meets unparalleled service.</p>
			</div>
		</div>
		<hr>

		<!-- Footer -->
		<footer class="w3-row-padding w3-padding-32">
			<div class="w3-third">
				<h3>CONTACT US</h3>
				<p>Phone	: 0948915051</p>
				<p>Email: </p>
				<p>n20dccn015@student.ptithcm.edu.vn   n20dccn043@student.ptithcm.edu.vn</p>
				<p>n20dccn055@student.ptithcm.edu.vn   n20dccn083@student.ptithcm.edu.vn</p>
				
			</div>

			<div class="w3-third">
				<h3>BLOG POSTS</h3>
				<ul class="w3-ul w3-hoverable">
					<li class="w3-padding-16"><img src="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-cola-16.png"
						class="w3-left w3-margin-right" style="width: 50px"> <span
						class="w3-large">Cocacola</span><br> <span>main sponsor</span></li>
					<li class="w3-padding-16"><img src="${pageContext.servletContext.contextPath}/resource/images/home/freshDrop.jpg"
						class="w3-left w3-margin-right" style="width: 50px"> <span
						class="w3-large">FreshDrop</span><br> <span>
							distribution</span></li>
				</ul>
			</div>

			<div class="w3-third w3-serif">
				<h3>POPULAR TAGS</h3>
				<p>
					<span class="w3-tag w3-black w3-margin-bottom">Cocacola</span> <span
						class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Rockstar</span> <span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Dinner</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Salmon</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Lemonnade</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Drinking</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Ideas</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Flavors</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Cuisine</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Summers</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Winter</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Monster</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Teaplus</span>
					<span class="w3-tag w3-dark-grey w3-small w3-margin-bottom">Milk</span>
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