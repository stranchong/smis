$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#username").focus();
	$.each($loginChildBottom.find("input"), addEventListener);
	$loginChildBottom.find("#submit_btn").click(btnOnClick);
	var checkObj = $loginChildBottom.find("#checkImage");
	checkObj.mouseover(function() {
		checkObj.addClass('onFocus');
	}).mouseout(function() {
		checkObj.removeClass('onFocus');
	});
});

function btnOnClick() {
	var $username = $("#username");
	var $password = $("#password");
	var $tip = $("#tip");

	var username = $.trim($username.val());
	var password = $.trim($password.val());
	if (username == "") {
		$tip.text("账号不能为空");
		$username.focus();
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

	$tip.text("正在登录...");
	$.post("manager/login.html", {
		"username" : username,
		"password" : password
	}, function(result) {
		if (!result.success) {
			$tip.text(result.msg);
			return;
		}
		window.location = "manager/index.html?mid=" + result.obj + "&username="
				+ encodeURIComponent(username);
	}, "json");
}