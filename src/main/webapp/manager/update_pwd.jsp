<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp"%>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/update_pwd.js"></script>
</head>
<body id="loginBody">
	<div id="loginBezel" style="height: 300px;">
		<div id="loginChildTop">
			<img src="images/logo-right.png" width="57" height="50" align="absbottom" />
			管理员密码修改
		</div>
		<div id="loginChildBottom">
			<table cellpadding="0" cellspacing="0" width="100%" height="95%" border="0">
				<tr>
					<td colspan="2">
						<span id="tip" class="tip">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">新密码：</td>
					<td align="left" width="65%">
						<input id="password" class="inputUP" type="password" placeholder="输入新密码" />
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">确&nbsp;认：</td>
					<td align="left" width="65%">
						<input id="confirm_password" class="inputUP" type="password" placeholder="确认输入新密码" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="mid" type="hidden" value="${manager.mid}" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" class="button" onclick="btnOnclick()" value="修　改" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>