<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../page_head.jsp"%>
<body>
	<div id="topMenu">
		<div class="search">
			<form id="search_form" action="manager/${moduleName}/page/1/10.html" method="post">
				<span>
					班级代号：
					<input name="clid" value="${clazz.clid}"/>
				</span>
				<span>
					班级名称：
					<input name="clname" value="${clazz.clname}"/>
				</span>
				<span>
					<input id="search_btn" type="submit" value="查询" />
				</span>
			</form>
			<form id="search_form_2" method="post">
				<input type="hidden" name="clid" value="${clazz.clid}">
				<input type="hidden" name="clname" value="${clazz.clname}">
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
					<th width="10%">序号</th>
					<th width="35%">班级代号</th>
					<th width="35%">班级名称</th>
					<th id="clearTDrightBorder" width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classes}" var="item" varStatus="status">
					<tr ${status.count % 2 == 0 ? "class=\"even\"" : ""}>
						<td>${status.count + pageInfo.currRecordIndex}</td>
						<td>${item.clid}</td>
						<td>${item.clname}</td>
						<td id="clearTDrightBorder">
							<span>
								<a href="manager/${moduleName}/update/show.html?clid=${item.clid}" target="_blank">&nbsp;修改&nbsp;</a>
							</span>
							&nbsp;
							<span>
								<a href="manager/${moduleName}/${item.clid}/delete.html">&nbsp;删除&nbsp;</a>
							</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../page_foot.jsp"%>