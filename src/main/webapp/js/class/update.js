$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#clname").focus();
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

	$tip.text("正在修改...");
	$.post("manager/class/update.html", {
		"clid" : clid,
		"clname" : clname
	}, function(result) {
		$tip.text(result.msg);
	}, "json");
}