$(function() {
	var $nav = $("#nav");
	$nav.find("a").click(function(){
		$nav.find(".on").removeClass("on");
		$(this).parent("li").addClass("on");
	});
});