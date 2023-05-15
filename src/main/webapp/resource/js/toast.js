/**
 * 
 */

const main = document.getElementById('toast');
if (main) {
	var toast = main.querySelector('.toast')
	setTimeout(function() {
		main.removeChild(toast)
	}, 7000)
}
	