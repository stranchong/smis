<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../page_head.jsp"%>
<body>
	<div id="topMenu">
		<div class="search">
			<form id="search_form" action="manager/${moduleName}/page/1/10.html" method="post">
				<span>
					课程代号：
					<input name="cid" value="${course.cid}" />
				</span>
				<span>
					课程名称：
					<input name="cname" value="${course.cname}" />
				</span>
				<span>
					总学分：
					<input name="credit" value="${course.credit}" />
				</span>
				<span>
					总学时：
					<input name="creditHours" value="${course.creditHours}" />
				</span>
				<span>
					<input id="search_btn" type="submit" value="查询" />
				</span>
			</form>
			<form id="search_form_2" method="post">
				<input type="hidden" name="cid" value="${course.cid}">
				<input type="hidden" name="cname" value="${course.cname}">
				<input type="hidden" name="credit" value="${course.credit}">
				<input type="hidden" name="creditHours" value="${course.creditHours}">
			</form>
		</div>
		<div class="add">
			<span>
				<a href="manager/${moduleName}/add/show.html" target="_blank">录入</a>
			</span>
		</div>
	</div>
	<div id="tableMain">
		<table class="center-table" border="0px" cellpadding="0" align="center" cellspacing="0">
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th width="20%">课程代号</th>
					<th width="20%">课程名称</th>
					<th width="20%">总学分</th>
					<th width="20%">总学时</th>
					<th id="clearTDrightBorder" width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="item" varStatus="status">
					<tr ${status.count % 2 == 0 ? "class=\"even\"" : ""}>
						<td>${status.count + pageInfo.currRecordIndex}</td>
						<td>${item.cid}</td>
						<td>${item.cname}</td>
						<td>${item.credit}</td>
						<td>${item.creditHours}</td>
						<td id="clearTDrightBorder">
							<span>
								<a href="manager/${moduleName}/update/show.html?cid=${item.cid}" target="_blank">&nbsp;修改&nbsp;</a>
							</span>
							&nbsp;
							<span>
								<a href="manager/${moduleName}/${item.cid}/delete.html">&nbsp;删除&nbsp;</a>
							</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="../page_foot.jsp"%>