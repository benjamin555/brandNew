<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案例进度管理</title>
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
		<li class="active"><a href="${ctx}/bg/bgCaseFollow/?caseId=${bgCaseFollow.caseId}">案例进度列表</a></li>
		<shiro:hasPermission name="bg:bgCaseFollow:edit"><li><a href="${ctx}/bg/bgCaseFollow/form?caseId=${bgCaseFollow.caseId}">案例进度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bgCaseFollow" action="${ctx}/bg/bgCaseFollow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input id="caseId" name="caseId" type="hidden" value="${bgCaseFollow.caseId}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>描述</th>
				<shiro:hasPermission name="bg:bgCaseFollow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bgCaseFollow">
			<tr>
				<td><a href="${ctx}/bg/bgCaseFollow/form?id=${bgCaseFollow.id}">
					<fmt:formatDate value="${bgCaseFollow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bgCaseFollow.descrip}
				</td>
				<shiro:hasPermission name="bg:bgCaseFollow:edit"><td>
    				<a href="${ctx}/bg/bgCaseFollow/form?id=${bgCaseFollow.id}">修改</a>
					<a href="${ctx}/bg/bgCaseFollow/delete?id=${bgCaseFollow.id}" onclick="return confirmx('确认要删除该案例进度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div> <a href="${ctx}/bg/bgCase/?id=${bgCaseFollow.caseId}" class="btn btn-primary">返回案例</a> </div>
</body>
</html>