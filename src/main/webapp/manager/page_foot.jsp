<%@ page language="java" pageEncoding="UTF-8"%>
<div id="page">
	<ul>
		<li>
			<a href="manager/${moduleName}/page/1/10.html">&nbsp;首页&nbsp;</a>
		</li>
		<li>
			<a href="manager/${moduleName}/page/${pageInfo.prevPage}/${pageInfo.pageSize}.html">&nbsp;上一页&nbsp;</a>
		</li>
		<li>
			<a href="manager/${moduleName}/page/${pageInfo.nextPage}/${pageInfo.pageSize}.html">&nbsp;下一页&nbsp;</a>
		</li>
		<li>
			<a href="manager/${moduleName}/page/${pageInfo.totalPage}/${pageInfo.pageSize}.html">&nbsp;末页&nbsp;</a>
		</li>
		<li>当前第 ${pageInfo.currentPage} 页, 共 ${pageInfo.totalPage} 页, ${pageInfo.totalCount} 条信息</li>
	</ul>
	<div>
		第
		<input id="jump_page" value="${pageInfo.currentPage}">
		页
		<input id="jump_btn" type="button" value="跳转" onclick="">
		<input id="pageSize" type="hidden" value="${pageInfo.pageSize}">
		<input id="totalPage" type="hidden" value="${pageInfo.totalPage}">
		<input id="moduleName" type="hidden" value="${moduleName}">
	</div>
</div>
</body>
</html>