<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
adodjkfdsfkjkljdfsf
</body>

<script>
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
			style: 'display: none;',
			name: 'productsId[]'
		})
	),
	// Cột giá của sản phẩm
	$('<td>').append(
		$('<input>').attr({
			value: productSelectedPrice,
			name: 'productsPrice[]',
			type: 'number',
			min: 0
		}).addClass('form-control').on('input', calculateTotalOnPrice)
	),
	// Cột số lượng sản phẩm
	$('<td>').append(
		$('<input>').attr({
			value: 1,
			name: 'productsQuantity[]',
			type: 'number',
			min: 0,
			max: productSelectedCurrenCount
		}).addClass('form-control').on('input',  calculateTotalOnQuantity)	
	)
	,
	// Cột thành tiền = price * quantity
	$('<td>').append(
		$('<input>').attr({
			value: productSelectedPrice, 
			readonly: 'readonly'
		}).addClass('form-control')	
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
</script>
</html>