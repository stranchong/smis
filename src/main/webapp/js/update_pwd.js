$(function() {
	$("#password").focus();
	$.each($("#loginChildBottom").find("input"), addEventListener);
});

function btnOnClick() {
	var $loginChildBottom = $("#loginChildBottom");
	var $tip = $loginChildBottom.find("#tip");
	var $mid = $loginChildBottom.find("#mid");
	var $password = $loginChildBottom.find("#password");
	var $confirm_password = $loginChildBottom.find("#confirm_password");

	var mid = $.trim($mid.val());
	var password = $.trim($password.val());
	var confirm_password = $.trim($confirm_password.val());
	var data = {
		"mid" : mid,
		"password" : password
	};

	if (password == "") {
		$tip.text("新密码不能为空");
		$password.focus();
		return;
	} else if (password.length < 6 || password.length > 20) {
		$tip.text("密码位数必须在6到20之间");
		$password.focus();
		return;
	} else if (password != confirm_password) {
		$tip.text("确认密码不一致");
		$confirm_password.focus();
		return;
	} 

	$tip.fadeOut("fast");
	$.post("manager/update.html", data, function(result) {
		$tip.text(result.msg).fadeIn("slow");
	}, "json");
}