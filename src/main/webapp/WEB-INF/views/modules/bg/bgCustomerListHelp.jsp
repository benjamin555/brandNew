<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
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
		<li class="active"><a href="${ctx}/bg/bgCustomer/">客户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bgCustomer" action="${ctx}/bg/bgCustomer/listHelp" method="post" class="breadcrumb form-search">
		
		<input id="select" name="select" type="hidden" value="${param.select}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li><label>联系人手机：</label>
				<form:input path="bgContacterList[0].mobileNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li><label>联系人：</label>
				<form:input path="bgContacterList[0].name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>更新时间</th>
				<th>备注</th>
				<th>名称</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bgCustomer">
			<tr>
				<td>
					<input type="radio"  value="${bgCustomer.id} "  descri="${bgCustomer.name}" />
				</td>
				<td><a href="${ctx}/bg/bgCustomer/form?id=${bgCustomer.id}">
					<fmt:formatDate value="${bgCustomer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bgCustomer.remarks}
				</td>
				<td>
					${bgCustomer.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>