<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../page_head.jsp"%>
<body>
	<div id="topMenu">
		<div class="search">
			<form id="search_form" action="manager/${moduleName}/page/1/10.html" method="post">
				<span>
					学号：
					<input name="sid" value="${student.sid}" />
				</span>
				<span>
					姓名：
					<input name="sname" value="${student.sname}" />
				</span>
				<span>
					班级：
					<select name="clid">
						<c:forEach items="${classes}" var="clazz">
							<option value="${clazz.clid}" ${clazz.clid == student.clid ? "selected" : ""}>${clazz.clname }</option>
						</c:forEach>
					</select>
				</span>
				<span>
					<input id="search_btn" type="submit" value="查询" />
				</span>
			</form>
			<form id="search_form_2" method="post">
				<input type="hidden" name="sid" value="${student.sid}">
				<input type="hidden" name="sname" value="${student.sname}">
				<input type="hidden" name="clid" value="${student.clid}">
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
					<th width="9%">序号</th>
					<th width="27%">学号</th>
					<th width="27%">姓名</th>
					<th width="27%">班级</th>
					<th id="clearTDrightBorder" width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="item" varStatus="status">
					<tr ${status.count % 2 == 0 ? "class=\"even\"" : ""}>
						<td>${status.count + pageInfo.currRecordIndex}</td>
						<td>${item.sid}</td>
						<td>${item.sname}</td>
						<td>${item.clazz.clname}</td>
						<td id="clearTDrightBorder">
							<span>
								<a href="manager/${moduleName}/update/show.html?sid=${item.sid}" target="_blank">&nbsp;修改&nbsp;</a>
							</span>
							&nbsp;
							<span>
								<a href="manager/${moduleName}/${item.sid}/delete.html">&nbsp;删除&nbsp;</a>
							</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="../page_foot.jsp"%>