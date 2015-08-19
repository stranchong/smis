function addEventListener() {
	var obj = $(this);
	obj.mouseover(function() {
		obj.addClass('onFocus');
	}).mouseout(function() {
		obj.removeClass('onFocus');
	}).keypress(function(event) {
		if (event.which == 13) {
			btnOnClick();
		}
	});
}