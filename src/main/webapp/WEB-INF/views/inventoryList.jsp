<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}	
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

.inventory-capability {
	max-height: 20vh;
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
	background: #fff;
}

.inventory-list__item {
	position: relative;
	cursor: pointer;
}

.inventory-list__item:hover .inventory-list__item-delete-btn {
	display: block;
}

.inventory-list__item-delete-btn {
	position: absolute;
	top: 8px;
	right: 4px;
	display: none;
}


</style>
<body>
	<nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
  <a href="javascript:void(0)" onclick="w3_close()"
  class="w3-bar-item w3-button">Close Menu</a>
  <a href="${pageContext.servletContext.contextPath}/" class="w3-bar-item w3-button">Trang chủ</a>
  <a href="${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm" class="w3-bar-item w3-button">Danh sách kho hàng</a>
  <a href="${pageContext.servletContext.contextPath}/san-pham/danh-sach.htm" class="w3-bar-item w3-button">Danh sách sản phẩm</a>
</nav>
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>

    <div class="w3-center w3-padding-16">Danh sách kho hàng</div>
  </div>
</div>
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
								<tr  <c:if test="${i.id == inventory.id}">class="inventory-list__item info"</c:if> class="inventory-list__item"
									onclick="location.href='${pageContext.servletContext.contextPath}/kho-hang/danh-sach.htm?id=${i.id}'">
									<td>${i.id}</td>
									<td>${i.name}</td>
									<td>${i.address}</td>
									<td><fmt:formatNumber value="${i.rentPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
									<td>
										<button onclick="event.stopPropagation(); deleteInventory(${i.id});" type="button" title="Delete" class="inventory-list__item-delete-btn btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
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
					<div class="col-md-4">
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
							value="<fmt:formatNumber value="${inventory.rentPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/>" readonly>
					</div>
				</div>


				<hr>

				<div class="inventory-capability">
					<table class="table table-hover inventory-capability__list">
						<thead class="inventory-capability__list-heading">
							<tr>
								<th>Sản phẩm</th>
								<th>Số lượng tối đa</th>
								<th>Số lượng hiện tại</th>
								<th>Đơn vị</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inventoryCapability"
								items="${inventory.inventoryCapability}">
								<tr>
									<td>${inventoryCapability.embeddedId.product.name}</td>
									<td>${inventoryCapability.maxCount}</td>
									<td>${inventoryCapability.currentCount}</td>
									<td>${inventoryCapability.embeddedId.product.unit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<c:if test="${inventory.id != 0}">
					<div class="row">				
						<div class="mt-12 col-md-2">
							<button type="button" class="btn btn-info" onclick="location.href='${pageContext.servletContext.contextPath}/kho-hang/chinh-sua.htm?id=${inventory.id}'">Chỉnh sửa</button>
						</div>	
						<div class="mt-12 col-md-offset-8 col-md-2">
							<button onclick="deleteInventory(${inventory.id});" type="button" title="Delete" class="btn btn-danger">
								Xóa
							</button>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
<!-- 	<svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="animate-spin text-center mx-auto mt-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><line x1="12" y1="2" x2="12" y2="6"></line><line x1="12" y1="18" x2="12" y2="22"></line><line x1="4.93" y1="4.93" x2="7.76" y2="7.76"></line><line x1="16.24" y1="16.24" x2="19.07" y2="19.07"></line><line x1="2" y1="12" x2="6" y2="12"></line><line x1="18" y1="12" x2="22" y2="12"></line><line x1="4.93" y1="19.07" x2="7.76" y2="16.24"></line><line x1="16.24" y1="7.76" x2="19.07" y2="4.93"></line></svg> -->
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/filter.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	
	<script>
	
		function deleteInventory(inventoryId) {
			var ok = confirm('Bạn có chắc muốn xóa kho hàng này?');
			if (ok) {
				location.href = "${pageContext.servletContext.contextPath}/kho-hang/xoa.htm?id=" + inventoryId;
			}
		}
	</script>
	<script>	
		window.addEventListener('load', () => {
		 	const targetElement = document.querySelector('.inventory-list__item.info');
		  	if (targetElement) {
		    	targetElement.scrollIntoView({
		      	behavior: 'smooth',
		      	block: 'center'
		    });
		  }
		});
	</script>
	<script>
	function w3_open() {
		  document.getElementById("mySidebar").style.display = "block";
		}
		 
		function w3_close() {
		  document.getElementById("mySidebar").style.display = "none";
		}
	</script>
</body>
</html>