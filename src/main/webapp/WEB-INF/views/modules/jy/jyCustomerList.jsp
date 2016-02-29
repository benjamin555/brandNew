<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户进度管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="${active eq 'listSelf'?'active':''}"><a href="${ctx}/jy/jyCustomer/listSelf">客户进度列表</a></li>
		<shiro:hasPermission name="jy:jyCustomer:superView"><li class="${active eq 'list'?'active':''}"><a href="${ctx}/jy/jyCustomer/list">总客户进度列表</a></li></shiro:hasPermission>
		<shiro:hasPermission name="jy:jyCustomer:edit"><li><a href="${ctx}/jy/jyCustomer/form">客户进度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="jyCustomer" action="${ctx}/jy/jyCustomer/${active}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>代理项目：</label>
				<form:input path="agent" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="jy:jyCustomer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jyCustomer">
			<tr>
				<td><a href="${ctx}/jy/jyCustomer/form?id=${jyCustomer.id}">
					<fmt:formatDate value="${jyCustomer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${jyCustomer.remarks}
				</td>
				<shiro:hasPermission name="jy:jyCustomer:edit"><td>
    				<a href="${ctx}/jy/jyCustomer/form?id=${jyCustomer.id}">修改</a>
					<a href="${ctx}/jy/jyCustomer/delete?id=${jyCustomer.id}" onclick="return confirmx('确认要删除该客户进度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>