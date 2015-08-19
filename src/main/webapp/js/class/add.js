$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#clid").focus();
	$.each($loginChildBottom.find("input"), addEventListener);
	$loginChildBottom.find("#submit_btn").click(btnOnClick);
});

function btnOnClick() {
	var $loginChildBottom = $("#loginChildBottom");
	var $tip = $loginChildBottom.find("#tip");
	var $clid = $loginChildBottom.find("#clid");
	var $clname = $loginChildBottom.find("#clname");

	var clid = $.trim($clid.val());
	var clname = $.trim($clname.val());
	if (clid == "") {
		$tip.text("班级代号不能为空");
		$clid.focus();
		return;
	} else if (clname == "") {
		$tip.text("班级名称不能为空");
		$clname.focus();
		return;
	}

	$tip.text("正在录入...");
	$.post("manager/class/add.html", {
		"clid" : clid,
		"clname" : clname
	}, function(result) {
		$tip.text(result.msg);
	}, "json");
}