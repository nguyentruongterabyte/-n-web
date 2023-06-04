/*
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
		
		var $filteredRows = $rows.filter(function() {
			var value = $(this).find('td').eq(column).text().toLowerCase();
			return value.indexOf(inputContent) === -1;
		});

	
		$table.find('tbody .no-result').remove();

		$rows.show();
		$filteredRows.hide();
		if ($filteredRows.length === $rows.length) {
			$table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">Không tìm thấy kết quả nào!</td></tr>'));
		}
	})
});
 */

 
 
 $(document).ready(function() {
  $('.filterable .btn-filter').click(function() {
    var $panel = $(this).parents('.filterable');
    var $filters = $panel.find('.filters input');
    var $tbody = $panel.find('.table tbody');

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
    var code = e.keyCode || e.which;
    if (code == '9') return;

    var $input = $(this);
    var inputContent = $input.val().toLowerCase();
    var $panel = $input.parents('.filterable');
    var $table = $panel.find('.table');
    var $rows = $table.find('tbody tr');
    var indices = [];

    $rows.each(function(index) {
      var $row = $(this);
      var matches = true;

      $row.find('td').each(function() {
        var value = $(this).text().toLowerCase();
        if (value.indexOf(inputContent) === -1) {
          matches = false;
          return false; // Thoát khỏi vòng lặp khi không phù hợp
        }
      });

      if (matches) {
        indices.push(index);
      }
    });

    $table.find('tbody .no-result').remove();
    $rows.hide();

    $.each(indices, function(index, value) {
      $rows.eq(value).show();
    });

    if (indices.length === 0) {
      $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">Không tìm thấy kết quả nào!</td></tr>'));
    }
  });
});
