$(function() {
	$.each($("#search_form").find("input"), addEventListener);

	var $search_form_2 = $("#search_form_2");
	var $page = $("#page");
	$page.find("a").click(function(event) {
		event.preventDefault();
		$search_form_2.attr("action", $(this).attr("href")).submit();
	});

	var $jump_page = $page.find("#jump_page");
	$jump_page.keyup(function() {
		if ($(this).val() == 0) {
			$(this).val("1");
		}
		$(this).val($(this).val().replace(/\D/g, ""));
	});

	var $pageSize = $page.find("#pageSize");
	var $totalPage = $page.find("#totalPage");
	var $moduleName = $page.find("#moduleName");
	$page.find("#jump_btn").click(function() {
		var href = "manager/" + $moduleName.val() + "/page/";
		if ($jump_page.val() > $totalPage.val()) {
			href += $totalPage.val() + "/" + $pageSize.val() + ".html";
		} else {
			href += $jump_page.val() + "/" + $pageSize.val() + ".html";
		}
		$search_form_2.attr("action", href).submit();
	});
});

function btnOnClick() {
	$("#searchForm").submit();
}