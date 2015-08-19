<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<div class="bezel" id="bezel-id">
		<div class="head">
			<div class="head-head">
				<div class="head-head-left">
					<span class="head-icon">警校学生管理系统-后台</span>
				</div>
				<div class="head-head-right">
					<span>
						管理员：${manager.username} 您好，欢迎登录使用！
						<a class="outLogin" href="manager/update-pwd/show.html?mid=${manager.mid}" target="_blank">修改密码</a>
						<a class="outLogin" href="manager/logout.html">退出</a>
					</span>
				</div>
			</div>
			<div class="head-foot">
				<ul id="nav">
					<li class="on">
						<a href="manager/student/page/1/10.html" target="center_window">学生管理</a>
					</li>
					<li>
						<a href="manager/teacher/page/1/10.html" target="center_window">教师管理</a>
					</li>
					<li>
						<a href="manager/class/page/1/10.html" target="center_window">班级管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">课程管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">院系管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">等级管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">角色管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">管理员管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">学生请假管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">学生选课管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">教师所教班级管理</a>
					</li>
					<li>
						<a href="manager/course/page/1/10.html" target="center_window">教师所管班级管理</a>
					</li>
				</ul>
			</div>
		</div>
		<iframe id="center_window" name="center_window" class="center" src="manager/student/page/1/10.html" framespacng="0" frameborder="0"> </iframe>
		<div class="foot">版权</div>
	</div>
</body>
</html>