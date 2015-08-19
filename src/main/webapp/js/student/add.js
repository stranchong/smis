$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#sid").focus();
	$.each($loginChildBottom.find("input"), addEventListener);
	$loginChildBottom.find("#submit_btn").click(btnOnClick);
});

function btnOnClick() {
	var $loginChildBottom = $("#loginChildBottom");
	var $tip = $loginChildBottom.find("#tip");
	var $sid = $loginChildBottom.find("#sid");
	var $sname = $loginChildBottom.find("#sname");
	var $password = $loginChildBottom.find("#password");
	var $clid = $loginChildBottom.find("#clid");

	var sid = $.trim($sid.val());
	var sname = $.trim($sname.val());
	var password = $.trim($password.val());
	var clid = $clid.val();
	if (sid == "") {
		$tip.text("学号不能为空");
		$sid.focus();
		return;
	} else if (sid.length != 12) {
		$tip.text("学号长度必须为12");
		$sid.focus();
		return;
	} else if (sname == "") {
		$tip.text("姓名不能为空");
		$sname.focus();
		return;
	} else if (password == "") {
		$tip.text("密码不能为空");
		$password.focus();
		return;
	} else if (password.length < 6 || password.length > 20) {
		$tip.text("密码位数必须在6到20之间");
		$password.focus();
		return;
	}

	$tip.text("正在录入...");
	$.post("manager/student/add.html", {
		"sid" : sid,
		"sname" : sname,
		"password" : password,
		"clid" : clid
	}, function(result) {
		$tip.text(result.msg);
	}, "json");
}