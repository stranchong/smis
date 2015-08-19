<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../head.jsp"%>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/class/update.js"></script>
</head>
<body id="loginBody">
	<div id="loginBezel" style="height: 350px;">
		<div id="loginChildTop">
			<img src="images/logo-right.png" width="57" height="50" align="absbottom" />
			班级信息编辑
		</div>
		<div id="loginChildBottom">
			<table cellpadding="0" cellspacing="0" width="100%" height="95%" border="0">
				<tr>
					<td colspan="2">
						<span id="tip" class="tip">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">班级代号：</td>
					<td align="left" width="65%">
						<input id="clid" class="inputUP" value="${clazz.clid }" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">班级名称：</td>
					<td align="left" width="65%">
						<input id="clname" class="inputUP" value="${clazz.clname }" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input id="submit_btn" type="button" class="button" value="修　改" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>