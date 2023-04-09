<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
	var html = 
		`<tr class="product-capability row row-no-padding" data-product-id="` + productId + `" data-product-name="` + productName +`">` 
		+	`<td class="product-capability__option col-md-1">
				<div class="product-capability__option-btn">
					<span class="glyphicon glyphicon-triangle-right"></span>
				</div>
				<div class="product-capability__option-btn-group">
					<div class="product-capability__option-btn-delete" onclick="deleteProduct(` + productId + `)">Xóa</div>
				</div>
			</td>`
		+	`<td class="col-md-5">`
		+		`<select class="form-control"
				name="productsId[]">`
				
		+			`<option selected="selected" value="` 
					+ productId	+ `">`
					+ productName + `</option>`	
		+ 	`</td>`
		+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="maxCounts[]"></td>`
		+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="lasts[]"></td>`
		+	`<td class="col-md-2"><input type="number" min="0" class="form-control" value="0" name="currentCounts[]"></td>`
	+	`</tr>`;
	
	$('#inventory-capability-input').before(html);
	
}); 
</script>
	</script>
</body>
</html>