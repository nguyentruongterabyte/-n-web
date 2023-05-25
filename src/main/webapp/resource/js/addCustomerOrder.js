/**
 * 
 */

$(document).ready(function() {
	$('#inventory').change(function() {
		window.location.href = contextPath + "/don-ban-hang/chon-kho-hang.htm?id=" + $(this).val();
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
							min: 0
						}).addClass('form-control').on('input', calculateTotalOnPrice),
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
							type: 'number',
							min: 0,
							max: productSelectedCurrenCount
						}).addClass('form-control').on('input', calculateTotalOnQuantity)
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

				// Hàm để sửa lại thành tiền khi số lượng sản phẩm thay đổi
				function calculateTotalOnQuantity() {
					var quantity = parseInt($(this).val());
					var price = parseInt($(this).closest('tr').find('td:nth-child(2) input:nth-child(2)').val());
					var total = quantity * price;
					var totalInput = $(this).closest('tr').find('td:nth-child(4) input');
					totalInput.val(total);
					formatCurrency($(totalInput));

					calculateTotalPrice();
				}

				// Hàm để sửa lại thành tiền khi giá sản phẩm thay đổi
				function calculateTotalOnPrice() {
					var price = convertCurrencyToInt($(this), "");
					console.log(price);
					var quantity = parseInt($(this).closest('tr').find('td:nth-child(3) input').val());
					var total = quantity * price;
					var totalInput = $(this).closest('tr').find('td:nth-child(4) input');
					totalInput.val(total);
					formatCurrency($(totalInput));

					calculateTotalPrice();
				}

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

	$('input[name="displayDiscount"]').on('input', function() {
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
	$('#total-price').val(totalPrice);
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

		let hiddenInput = $(displayInputSelector).siblings('input[name="' + hiddenInputSelector + '"]');
		if (hiddenInput) {
			hiddenInput.val(integerValue);
		}
		// Định dạng tiền tệ (vnđ)
		//let formattedPrice = integerValue.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }); 
		//displayInput.val(formattedPrice);
	}

	// Nếu giá trị không có gì thì gán giá trị hidden bằng 0
	if (!displayInput) {
		let hiddenInput = $(displayInputSelector).siblings('input[name="' + hiddenInputSelector + '"]');
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