/**
 * 
 */
$(document).ready(function() {
	$('.filterable .btn-filter').click(function() {
		var $panel = $(this).parents('.filterable'),
			$filters = $panel.find('.filters input'),
			$tbody = $panel.find('.table body');

		if ($filters.prop('disabled') == true) {
			$filters.prop('disabled', false);
			$filters.first().focus();
		} else {
			$filters.val('').prop('disabled', true);
			$tbody.find('.no-result').remove();
			$tbody.find('tr').show();
		}

	});

	$('.filterable .filters input').keyup(function(e) {
		/*Ignore key tab */
		var code = e.keyCode || e.which;
		// console.log(code)
		if (code == '9') return;
		var $input = $(this),
			inputContent = $input.val().toLowerCase(),
			$panel = $input.parents('.filterable'),
			// 				console.log($panel)
			column = $panel.find('.filters th').index($input.parents('th')),
			// console.log(column)
			$table = $panel.find('.table'),
			$rows = $table.find('tbody tr');
		/*Lọc*/
		var $filteredRows = $rows.filter(function() {
			var value = $(this).find('td').eq(column).text().toLowerCase();
			return value.indexOf(inputContent) === -1;
		});

		/* Xóa kết quả `no result` trước nếu nó tồn tại*/
		$table.find('tbody .no-result').remove();
		/* Hiển thị tất cả các row, ẩn các row đã lọc */
		$rows.show();
		$filteredRows.hide();
		if ($filteredRows.length === $rows.length) {
			$table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">Không tìm thấy kết quả nào!</td></tr>'));
		}
	})
});