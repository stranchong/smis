$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#sname").focus();
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
	var data = {
		"sid" : sid,
		"sname" : sname,
		"clid" : clid
	};

	if (sname == "") {
		$tip.text("姓名不能为空");
		$sname.focus();
		return;
	} else if (password != "") {
		if (password.length < 6 || password.length > 20) {
			$tip.text("密码位数必须在6到20之间");
			$password.focus();
			return;
		}
		data.password = password;
	}

	$tip.text("正在修改...");
	$.post("manager/student/update.html", data, function(result) {
		$tip.text(result.msg);
	}, "json");
}