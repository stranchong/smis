<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<title>警校学生管理系统-后台</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/login.js"></script>
</head>
<body id="loginBody">
	<div id="loginBezel">
		<div id="loginChildTop">
			<img src="images/logo-right.png" width="57" height="50" align="absbottom" />
			警校学生管理系统-后台登录
		</div>
		<div id="loginChildBottom">
			<table cellpadding="0" cellspacing="0" width="100%" height="95%" border="0">
				<tr>
					<td colspan="2">
						<span id="tip" class="tip">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">用户名：</td>
					<td align="left" width="65%">
						<input type="text" id="username" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="right">密 &nbsp;码：</td>
					<td align="left">
						<input type="password" id="password" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="right">验证码：</td>
					<td align="left">
						<input type="text" id="checkNum" />
						<span>
							<img src="images/checkNum.jpg" id="checkImage" align="absbottom" alt="验证码" title="点击更换验证码" />
						</span>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input id="submit_btn" type="button" class="button" onclick="btnOnclick()" value="登　录" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>