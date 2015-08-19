$(function() {
	var $loginChildBottom = $("#loginChildBottom");
	$loginChildBottom.find("#cname").focus();
	$.each($loginChildBottom.find("input"), addEventListener);
	$loginChildBottom.find("#credit").keyup(function() {
		if (isNaN($(this).val())) {
			$(this).val("");
		}
	});
	$loginChildBottom.find("#creditHours").keyup(function() {
		$(this).val($(this).val().replace(/\D/g, ""));
	});
	$loginChildBottom.find("#submit_btn").click(btnOnClick);
});

function btnOnClick() {
	var $loginChildBottom = $("#loginChildBottom");
	var $tip = $loginChildBottom.find("#tip");
	var $cid = $loginChildBottom.find("#cid");
	var $cname = $loginChildBottom.find("#cname");
	var $credit = $loginChildBottom.find("#credit");
	var $creditHours = $loginChildBottom.find("#creditHours");

	var cid = $.trim($cid.val());
	var cname = $.trim($cname.val());
	var credit = $.trim($credit.val());
	var creditHours = $.trim($creditHours.val());
	if (cid == "") {
		$tip.text("课程代号不能为空");
		$cid.focus();
		return;
	} else if (cname == "") {
		$tip.text("课程名称不能为空");
		$cname.focus();
		return;
	} else if (credit == "") {
		$tip.text("总学分不能为空");
		$credit.focus();
		return;
	} else if (!new RegExp("^\\d*(\\.(5|0))?$").test(credit)) {
		$tip.text("总学分必须是0.5的整数倍");
		$credit.focus();
		return;
	} else if (creditHours == "") {
		$tip.text("总学时不能为空");
		$creditHours.focus();
		return;
	} else if (creditHours < 1) {
		$tip.text("总学时必须大于0");
		$creditHours.focus();
		return;
	}

	$tip.text("正在修改...");
	$.post("manager/course/update.html", {
		"cid" : cid,
		"cname" : cname,
		"credit" : credit,
		"creditHours" : creditHours
	}, function(result) {
		$tip.text(result.msg);
	}, "json");
}