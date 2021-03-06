<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../head.jsp"%>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/student/update.js"></script>
</head>
<body id="loginBody">
	<div id="loginBezel" style="height: 350px;">
		<div id="loginChildTop">
			<img src="images/logo-right.png" width="57" height="50" align="absbottom" />
			学生信息编辑
		</div>
		<div id="loginChildBottom">
			<table cellpadding="0" cellspacing="0" width="100%" height="95%" border="0">
				<tr>
					<td colspan="2">
						<span id="tip" class="tip">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">学 &nbsp;号：</td>
					<td align="left" width="65%">
						<input id="sid" class="inputUP" value="${student.sid }" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td align="right" width="35%">姓 &nbsp;名：</td>
					<td align="left" width="65%">
						<input id="sname" class="inputUP" value="${student.sname }" />
					</td>
				</tr>
				<tr>
					<td align="right">密 &nbsp;码：</td>
					<td align="left">
						<input id="password" class="inputUP" type="password"  placeholder="密码可修改，可不修改"/>
					</td>
				</tr>
				<tr>
					<td align="right">班 &nbsp;级：</td>
					<td align="left">
						<select id="clid" class="select">
							<c:forEach items="${classes}" var="clazz">
								<option value="${clazz.clid}" ${clazz.clid == student.clid ? "selected" : ""}>${clazz.clname}</option>
							</c:forEach>
						</select>
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