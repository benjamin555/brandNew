<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>快件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var isAdmin = false;
			<shiro:hasRole name="a">
				isAdmin = true;
			</shiro:hasRole>
			if(!isAdmin){
				$(document).prohibitRight().prohibitCopy();
			}
			
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
		<li class="active"><a href="${ctx}/bg/bgExpress/">快件列表</a></li>
		<shiro:hasPermission name="bg:bgExpress:edit"><li><a href="${ctx}/bg/bgExpress/form">快件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bgExpress" action="${ctx}/bg/bgExpress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发件时间：</label>
				<input name="diliveryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgExpress.diliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>文件：</label>
				<form:input path="file" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="400" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="phoneNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>情况：</label>
				<form:input path="status" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false"  class="input-medium"/>
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
				<th>发件时间</th>
				<th>文件</th>
				<th>地址</th>
				<th>电话</th>
				<th>费用</th>
				<th>情况</th>
				<th>业务根据日</th>
				<th>跟进情况</th>
				<shiro:hasPermission name="bg:bgExpress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bgExpress">
			<tr>
				<td><a href="${ctx}/bg/bgExpress/form?id=${bgExpress.id}">
					<fmt:formatDate value="${bgExpress.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bgExpress.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bgExpress.diliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bgExpress.file}
				</td>
				<td>
					${bgExpress.address}
				</td>
				<td>
					${bgExpress.phoneNumber}
				</td>
				<td>
					${bgExpress.fee}
				</td>
				<td>
					${bgExpress.status}
				</td>
				<td>
					<fmt:formatDate value="${bgExpress.keeponDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bgExpress.keeponStatus}
				</td>
				<td>
    				<shiro:hasPermission name="bg:bgExpress:edit"><a href="${ctx}/bg/bgExpress/form?id=${bgExpress.id}">修改</a></shiro:hasPermission>
					<shiro:hasPermission name="bg:bgExpress:del"><a href="${ctx}/bg/bgExpress/delete?id=${bgExpress.id}" onclick="return confirmx('确认要删除该快件吗？', this.href)">删除</a></shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>