<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../head.jsp"%>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/course/add.js"></script>
</head>
<body id="loginBody">
	<div id="loginBezel" style="height: 350px;">
		<div id="loginChildTop">
			<img src="images/logo-right.png" width="57" height="50" align="absbottom" />
			课程信息录入
		</div>
		<div id="loginChildBottom">
			<table cellpadding="0" cellspacing="0" width="100%" height="95%" border="0">
				<tr>
					<td colspan="2">
						<span id="tip" class="tip">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">课程代号：</td>
					<td align="left" width="65%">
						<input id="cid" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">课程名称：</td>
					<td align="left" width="65%">
						<input id="cname" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">总学分：</td>
					<td align="left" width="65%">
						<input id="credit" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">总学时：</td>
					<td align="left" width="65%">
						<input id="creditHours" class="inputUP" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input id="submit_btn" type="button" class="button" value="录　入" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>