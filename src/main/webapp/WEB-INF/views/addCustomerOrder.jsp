<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đơn bán hàng</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.servletContext.contextPath}/resource/images/favicon/icons8-order-16.png">

<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
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

			<div class="w3-center w3-padding-16">Đơn bán hàng</div>
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
		<form action="${pageContext.servletContext.contextPath}/don-ban-hang/xac-thuc.htm" method="post">
			<div class="row mt-16">
				<div class="col-md-4">

					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h6>Sản phẩm</h6>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2">Kho chứa</div>
										<div class="col-md-10">
											<select id="inventory" name="inventory" class="form-control">
												<option></option>
												<c:forEach var="i" items="${inventories}">
													<option value="${i.id}" 
														<c:if test="${i.id == inventory.id}">
															selected="selected"
														</c:if>
														>${i.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row mt-16">
										<div class="col-md-2">Mặt hàng</div>
										<div class="col-md-10">
											<select id="product-selected" name="product"
												class="form-control">
												<c:choose>
													<c:when test="${inventory != null}">
														<c:forEach var="ic"
															items="${inventory.inventoryCapabilities}">
															<option class="product-option"
																data-name="${ic.embeddedId.product.name}"
																data-price="${ic.embeddedId.product.outPrice}"
																data-current-count="${ic.currentCount}" 
																value="${ic.embeddedId.product.id}">${ic.embeddedId.product.name}
																- còn ${ic.currentCount} ${ic.embeddedId.product.unit}</option>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<option value="">Vui lòng chọn kho chứa</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
									</div>
									<div class="row mt-16">
										<div class="col-md-4 col-md-offset-8">
											<button type="button" id="add-product-btn" class="btn-info btn btn-lg">Thêm>></button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h6>Khách hàng</h6>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-4">
											<label>Họ tên</label>
										</div>
										<div class="col-md-8">
											<select id="customer-selected" name="customer"
												class="form-control">
												<c:forEach var="c" items="${customers}">
													<option id="customer-id-${c.id}"
														data-customer-email="${c.email}"
														data-customer-phone="${c.phone}" 
														data-discount="${c.groupOfCustomer.discount}"
														value="${c.id}"
														<c:if test="${c.id == customer.id}">selected</c:if>
														>${c.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row mt-16">
										<div class="col-md-4">
											<label>Mã khách hàng</label>
										</div>
										<div class="col-md-8">
											<input name="customerId" readonly="readonly"
												class="form-control"
												<c:choose>
													<c:when test="${customer != null}">
														value="${customer.id}"											
													</c:when>
													<c:otherwise>
														<c:if test="${customers != null}">
															value="${customers[0].id}"
														</c:if>
													</c:otherwise>
												</c:choose>
					
												
												>

										</div>
									</div>
									<div class="row mt-16">
										<div class="col-md-4">
											<label>Số điện thoại</label>
										</div>
										<div class="col-md-8">
											<input name="customerPhone" readonly="readonly"
												class="form-control"
												<c:choose>
													<c:when test="${customer != null}">
														value="${customer.phone}"											
													</c:when>
													<c:otherwise>
														<c:if test="${customers != null}">
															value="${customers[0].phone}"
														</c:if>
													</c:otherwise>
												</c:choose>
											>
										</div>
									</div>
									<div class="row mt-16">
										<div class="col-md-4">
											<label>Email</label>
										</div>
										<div class="col-md-8">
											<input name="customerEmail" readonly="readonly"
												class="form-control"
												<c:choose>
													<c:when test="${customer != null}">
														value="${customer.email}"											
													</c:when>
													<c:otherwise>
														<c:if test="${customers != null}">
															value="${customers[0].email}"
														</c:if>
													</c:otherwise>
												</c:choose>
											>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-heading">Chi tiết hóa đơn</div>
						<div class="panel-body"
							style="max-height: 50vh; overflow-y: scroll;">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
										<th>Thành tiền</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="product-list">
									<c:forEach var="od" items="${orderDetails}">
										<tr id="product-id-${od.embeddedId.product.id}">
											<td>
												<input 
													class="form-control"
													type="text"
													readonly="readonly"
													value="${od.embeddedId.product.name}">
												<input type="hidden" 
													name="productsId[]"
													value="${od.embeddedId.product.id}"
												>
											</td>
											<td>
												<input value="${od.price}" 
													name="displayProductPrice" 
													onchange="formatCurrency(this); convertCurrencyToInt(this, 'productsPrice[]');"
													oninput="calculateTotalOnPrice(this)" 
													min="0" 
													class="form-control">
												<input 
													type="hidden"
													name="productsPrice[]"
													min="0"
													class="form-control"
													value="${od.price}"
												>
											</td>
											<td>
												<input 
													value="${od.quantity}"
													name="productsQuantity[]" 
													oninput="calculateTotalOnQuantity(this)" 
													class="form-control"
													type="number"
													min="0"
												>
											</td>
											<td>
												<input 
													class="form-control total-amount"
													readonly="readonly"
													value="${od.price * od.quantity}"
												>
											</td>
											<td>
												<button 
													type="button"
													title="Delete" 
													onclick="event.stopPropagation(); deleteProduct('${od.embeddedId.product.id}');"
													class="btn btn-danger btn-xs"
												>
													<span class="glyphicon glyphicon-trash"></span>
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="col-md-8">
					<div class="row mt-16">
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-4">
									<label>Thành tiền sản phẩm</label>
								</div>
								<div class="col-md-8">
									<input 
										id="total-price" 
										readonly="readonly"
										name="displayTotalPrice"
										value="${totalPrice}"						
										class="form-control"
										>
									<input
										type="hidden"  
										readonly="readonly"
										name="totalPrice"
										value="${totalPrice}"
										>
								</div>
							</div>
							<div class="row mt-12">
								<div class="col-md-4">
									<label>Tổng hóa đơn</label>
								</div>
								<div class="col-md-8">
								
									<input id="final-price" name="finalPrice" class="form-control"
										readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-6">
									<div class="row">
										<div class="col-md-4">
											<label>Chiết khấu</label>
										</div>
										<div class="col-md-8">
											<input class="form-control"
												<c:choose>
													<c:when test="${discount != null}">
														value="${Double.parseDouble(discount) * 100}%"
													</c:when>
													<c:when test="${customer != null}">
														value="${Double.parseDouble(customer.groupOfCustomer.discount) * 100}%"
													</c:when>
													<c:otherwise>
														<c:if test="${customers != null}">
															value="${Double.parseDouble(customers[0].groupOfCustomer.discount) * 100}%"
														</c:if>
													</c:otherwise>
												</c:choose> 
												
												name="displayDiscount"
												id="discount"> 
											<input name="discount"
												type="hidden"
												<c:choose>
													<c:when test="${discount != null}">
														value="${Double.parseDouble(discount)}"
													</c:when>
													<c:when test="${customer != null}">
														value="${Double.parseDouble(customer.groupOfCustomer.discount)}"
													</c:when>
													<c:otherwise>
														<c:if test="${customers != null}">
															value="${Double.parseDouble(customers[0].groupOfCustomer.discount)}"
														</c:if>
													</c:otherwise>
												</c:choose> 
											>											
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row">
										<div class="col-md-4">
											<label for="VAT">VAT</label>
										</div>
										<div class="col-md-8">
											<input name="displayVAT" 
													class="form-control" 
													<c:choose>
														<c:when test="${VAT != null}">
															value="${Double.parseDouble(VAT) * 100}%"
														</c:when>
														<c:otherwise>
															value="10%"
														</c:otherwise>
													</c:choose>
													>
											<input type="hidden" 
													name="VAT" 
													<c:choose>
														<c:when test="${VAT != null}">
															value="${Double.parseDouble(VAT)}"
														</c:when>
														<c:otherwise>
															value="0.1"
														</c:otherwise>
													</c:choose>
													id="VAT"
													
													>
										</div>
									</div>
								</div>
							</div>
							<div class="row mt-16">
								<div class="col-md-3">
									<label for="extraPaid">Phụ thu</label>
								</div>
								<div class="col-md-9">
									<input type="number" class="form-control" value="0"
										name="extraPaid" id="extraPaid">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8 mt-16">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h6>Thanh toán</h6>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="row">
												<div class="col-md-4">
													<label for="payment">Hình thức thanh toán</label>
												</div>
												<div class="col-md-8">
													<select id="payment" class="form-control" name="payment">
														<option value="cash">Tiền mặt</option>
														<option value="bank">Ngân hàng</option>
														<option value="momo">Momo</option>
														<option value="zalopay">Zalo pay</option>
													</select>
												</div>
											</div>
											<div class="row mt-16">
												<div class="col-md-4">
													<label for="received-money"> Số tiền nhận </label>
												</div>
												<div class="col-md-8">
													<input type="number" class="form-control"
														name="receivedMoney" id="received-money">
												</div>
											</div>
											<div class="row mt-16">
												<div class="col-md-4">
													<label for="change-money"> Số tiền trả lại </label>
												</div>
												<div class="col-md-8">
													<input readonly="readonly" type="number"
														class="form-control" name="changeMoney" id="changeMoney">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row">
												<div class="col-md-4">
													<label>Trạng thái đơn hàng</label>
												</div>
												<div class="col-md-8">
													<select name="status" id="status" class="form-control">
														<option value="paid">Đã thanh toán</option>
														<option value="canceled">Đã hủy</option>
													</select>
												</div>
											</div>
											<div class="row" style="margin-top: 60px;">
												<div class="col-md-4 col-md-offset-7">
													<button class="btn btn-success btn-lg">Hoàn thành</button>
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</form>
		
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resource/js/toast.js"></script>
	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#inventory').change(function() {
				var ok = confirm("Chuyển kho chứa dữ liệu nhập sẽ mất! Xác nhận?");
				if (ok) {				
					window.location.href = "${pageContext.servletContext.contextPath}/don-ban-hang/chon-kho-hang.htm?id=" + $(this).val();
				}
			})
			
			
			// Sự kiện nút thêm sản phẩm vào danh sách đươc click
			$('#add-product-btn').click(function() {
				
				// Lấy sản phẩm được chọn 
				var productSelected = $('#product-selected')
				// Lấy id sản phẩm
				var productSelectedId = productSelected.val();
				// Nếu id sản phẩm tồn tại (tức được chọn)
				if (productSelectedId) {
					var checkProductExist = $('#product-id-' + productSelectedId)
					// Kiểm tra thẻ tr đã có chưa
					// Nếu chưa có thì append vào thẻ tbody
					if (checkProductExist.length <= 0) {
						// Lấy thẻ tbody để chuẩn bị append thẻ tr vào
						var productList = $('#product-list')
						
						// Lấy data-name từ thẻ option bằng value = id của sản phẩm
						var productSelectedName = $('option.product-option').filter('[value="' + productSelectedId + '"]').data('name');
						
						// Lấy data-price từ thẻ option bằng value = id của sản phẩm trong thẻ option đó
						var productSelectedPrice = $('option.product-option').filter('[value="' + productSelectedId + '"]').data('price');
						var productSelectedCurrenCount = $('option.product-option').filter('[value="' + productSelectedId + '"]').data('current-count')
						var newRow = $('<tr>').attr('id', 'product-id-' + productSelectedId).append(
							$('<td>').append(
								// Cột tên sản phẩm
								$('<input>').attr({
									value: productSelectedName,
									type: 'text',  
									readonly: 'readonly'
								}).addClass('form-control'),
								// Cột id của sản phẩm với display: none
								$('<input>').attr({
									value: productSelectedId,
									type: 'hidden',
									name: 'productsId[]'
								})
							),
							// Cột giá của sản phẩm
							$('<td>').append(
								$('<input>').attr({
									value: productSelectedPrice,
									name: 'displayProductPrice',
									onchange: 'formatCurrency(this); convertCurrencyToInt(this, "productsPrice[]");',
									oninput: 'calculateTotalOnPrice(this)',
									min: 0
								}).addClass('form-control'),
								$('<input>').attr({
									type: 'hidden',
									name: 'productsPrice[]',
									value: productSelectedPrice
								})
							),
							// Cột số lượng sản phẩm
							$('<td>').append(
								$('<input>').attr({
									value: 1,
									name: 'productsQuantity[]',
									oninput: 'calculateTotalOnQuantity(this)',
									type: 'number',
									min: 0,
									max: productSelectedCurrenCount
								}).addClass('form-control')
							)
							,
							// Cột thành tiền = price * quantity
							$('<td>').append(
								$('<input>').attr({
									value: productSelectedPrice, 
									readonly: 'readonly'
								}).addClass('form-control total-amount')
								
							), 
							// Nút xóa sản phẩm khỏi danh sách
							$('<td>').append(
								$('<button>').attr({
									type: 'button',
									title: 'Delete',
									onclick: 'event.stopPropagation(); deleteProduct("' + productSelectedId + '");'
								}).addClass('btn btn-danger btn-xs').append(
									$('<span>').addClass('glyphicon glyphicon-trash')
								)
								
							)
							
						);
						
						productList.append(newRow);
					} else {
						var priceInput = checkProductExist.find('td:nth-child(2) input:nth-child(2)');
						var quantityInput = checkProductExist.find('td:nth-child(3) input');
						var totalInput = checkProductExist.find('td:nth-child(4) input');
						var currentQuantity = parseInt(quantityInput.val());
						quantityInput.val(currentQuantity + 1);
						totalInput.val((currentQuantity + 1) * priceInput.val());
						formatCurrency($(totalInput))
					}
					calculateTotalPrice(); 
				}
				
			});
			
			// Sự kiện thay đổi combobox khách hàng
			$('#customer-selected').change(function() {
				// Lấy id của khách hàng thông qua giá trị của thẻ select
				var customerId = $(this).val();
				// Tìm thẻ option có id là id của khách hàng được combobox chọn
				var customerOption = $('#customer-id-' + customerId);
				// Lấy data-customer-email từ thẻ option tìm được
				var customerEmail = customerOption.data('customer-email');
				
				// Lấy data-customer-phone từ thẻ option tìm được
				var customerPhone = customerOption.data('customer-phone');
				
				// Lấy data-discount từ thẻ option tìm được
				var customerDiscount = customerOption.data('discount');
				
				// Đổi value của thẻ input name=customerId thành id tương ứng với 
				// giá trị được chọn 
				$('input[name="customerId"]').val(customerId);
				
				// Tương tự thay đổi với customerPhone và customerEmail
				$('input[name="customerPhone"]').val(customerPhone);
				$('input[name="customerEmail"]').val(customerEmail);
				$('input[name="discount"]').val(customerDiscount);
				$('input[name="displayDiscount"]').val((parseFloat(customerDiscount) * 100) + '%')
				
			});
			
			$('input[name="displayDiscount"]').on('input',function() {
				// Lấy giá trị của thẻ input
				var inputValue = $(this).val();
				// Biến kiểm tra có tồn tại ký tự phần trăm ở trong chuỗi vừa nhập hay không
				var containsPercent = inputValue.includes("%");
				
				// Nếu không tồn tại thì thêm vào đuôi chuỗi vừa nhập
				if (!containsPercent) {
					
					$(this).val(inputValue + '%');
					// Thêm vào thẻ input type hidden giá trị float để đẩy về kiểu float bên server
					$('input[name="discount"]').val(parseFloat(inputValue / 100));				
				} else { // Nếu tồn tại thì chỉ cần thêm giá trị vào input discount hidden
					$(this).val(inputValue);
					$('input[name="discount"]').val(parseFloat(inputValue.split('%')[0] / 100));
				}
			});
			
			$('input[name="displayVAT"]').on('input', function() {
				 var inputValue = $(this).val();
				 var containsPercent = inputValue.includes('%');
				 
				 if (!containsPercent) {
					 $(this).val(inputValue + '%');
					 $('input[name="VAT"]').val(parseFloat(inputValue / 100));
				 } else {
					 $(this).val(inputValue);
					 $('input[name="VAT"]').val(parseFloat(inputValue.split('%')[0] / 100));
				 }
			})
			
		})
		
		// Hàm xóa thẻ tr khỏi DOM khi nút xóa được nhấn
		function deleteProduct(productId) {
			$('#product-id-' + productId).remove();
			calculateTotalPrice();
		}
		
		// Hàm tính thành tiền của sản phẩm
		function calculateTotalPrice() {
			var totalPrice = 0;

			// Lặp qua các thành tiền của mỗi sản phẩm trong giỏ hàng
			$('.total-amount').each(function() {
			    var inputValue = convertCurrencyToInt($(this), "");
			    // Cộng chúng vào totalPrice
			    totalPrice += isNaN(inputValue) ? 0 : inputValue;
			});
			// Thêm chúng vào input có id là total-price
			var displayTotalPriceInput = $('#total-price')
			var hiddenTotalPriceInput = $(displayTotalPriceInput).siblings('input[name="totalPrice"]');
			// Nếu tồn tại thẻ input hidden
			if (hiddenTotalPriceInput) {
				// Thêm giá trị thuần vào total price hidden để trả về cho server
				$(hiddenTotalPriceInput).val(totalPrice);
			}
			$(displayTotalPriceInput).val(totalPrice);
			formatCurrency($(displayTotalPriceInput));
		}
		
		// Hàm định dạng tiền tệ ở displayInput và thêm giá trị thuần ở hàm inputHidden
		// - Hiển thị tiền tệ ở displayInput
		// - Lấy và gán vào giá trị số vào thẻ hiddenInput
		// Bắt buộc: hai thẻ input phải nằm ngang hàng 
		// và chứa trong cùng một thẻ bao đóng
		function convertCurrencyToInt(displayInputSelector, hiddenInputSelector) {
			let displayInput = $(displayInputSelector).val();
			let integerValue = 0;
			if ($(displayInputSelector)) {
				
				// Biến kiểm tra xem trong displayInput có tồn tại ký tự khác chữ số không
				let containsNonDigit = /\D/.test(displayInput);
				
				// Nếu tồn tại ký tự khác chữ số loại bỏ nó ra khỏi chuỗi
				// và gán vào biến integerValue
				if (containsNonDigit) {
					// /[^\d] là biểu thức chính quy chỉ chứa các ký tự chữ số
					integerValue = parseInt(displayInput.replace(/[^\d]/g, ''), 10);
				} else {
					integerValue = parseInt(displayInput, 10);
				}
				
				// tìm thẻ input chứa giá trị để trả về server và thêm vào đó
				//  giá trị thuần
				
				let hiddenInput = $(displayInputSelector).siblings('input[name="'+ hiddenInputSelector +'"]');
				if (hiddenInput) {
					hiddenInput.val(integerValue);
				}
				// Định dạng tiền tệ (vnđ)
				//let formattedPrice = integerValue.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }); 
				//displayInput.val(formattedPrice);
			}
			
			// Nếu giá trị không có gì thì gán giá trị hidden bằng 0
			if (!displayInput)
				{
				let hiddenInput = $(displayInputSelector).siblings('input[name="'+ hiddenInputSelector +'"]');
				if (hiddenInput) {
					hiddenInput.val(0);
				}
			}
			return integerValue;
		}
		
		// Sử dụng cho hàm onchage trong các thẻ display
		function formatCurrency(inputSelector) {
			let inputValue = $(inputSelector).val();
			if (inputValue) {
				
				// Biến kiểm tra xem trong inputValue có tồn tại ký tự khác chữ số không
				let containsNonDigit = /\D/.test(inputValue);
				
				let integerValue = 0;
				// Nếu tồn tại ký tự khác chữ số loại bỏ nó ra khỏi chuỗi
				// và gán vào biến integerValue
				if (containsNonDigit) {
					// /[^\d] là biểu thức chính quy chỉ chứa các ký tự chữ số
					integerValue = parseInt(inputValue.replace(/[^\d]/g, ''), 10);
				} else {
					integerValue = parseInt(inputValue, 10);
				}
				
				// tìm thẻ input chứa giá trị để trả về server và thêm vào đó
				//  giá trị thuần
				
				// Định dạng tiền tệ (vnđ)
				let formattedPrice = integerValue.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }); 
				$(inputSelector).val(formattedPrice);
			} 
			
		}
		
		// Hàm để sửa lại thành tiền khi số lượng sản phẩm thay đổi
		function calculateTotalOnQuantity(selector) {
			var quantity = parseInt($(selector).val());
			var price = parseInt($(selector).closest('tr').find('td:nth-child(2) input:nth-child(2)').val());
			var total = quantity * price;
			var totalInput = $(selector).closest('tr').find('td:nth-child(4) input');
			totalInput.val(total);
			formatCurrency($(totalInput));
	
			calculateTotalPrice();
		}
		
		// Hàm để sửa lại thành tiền khi giá sản phẩm thay đổi
		function calculateTotalOnPrice(selector) {
			var price = convertCurrencyToInt($(selector), "");
			var quantity = parseInt($(selector).closest('tr').find('td:nth-child(3) input').val());
			var total = quantity * price;
			var totalInput = $(selector).closest('tr').find('td:nth-child(4) input');
			totalInput.val(total);
			formatCurrency($(totalInput));
			
			calculateTotalPrice();
		}
		
	</script>
</body>
</html>