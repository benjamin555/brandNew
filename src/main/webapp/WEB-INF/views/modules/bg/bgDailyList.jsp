<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日报管理</title>
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
		<li class="active"><a href="${ctx}/bg/bgDaily/">日报列表</a></li>
		<shiro:hasPermission name="bg:bgDaily:edit"><li><a href="${ctx}/bg/bgDaily/form">日报添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bgDaily" action="${ctx}/bg/bgDaily/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				<input name="riDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgDaily.riDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label style="width: 120px;">意向客户及情况：</label>
				<form:input path="todayRemark" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label style="width: 150px;">明天重点客户及情况：</label>
				<form:input path="tomorrowPlan" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
		
		<ul class="ul-form">
			<li><label >联系人：</label>
					<form:input path="bgDailyContacterList[0].name" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label style="width: 120px;">联系电话：</label>
				<form:input path="bgDailyContacterList[0].mobileNumber" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li>
			<label style="width: 120px;">代理事项：</label>
				<form:input path="agentJob" htmlEscape="false" maxlength="1000" class="input-medium"/>
			
			</li>
			<li class="btns"></li>
			<li class="clearfix"></li>
		</ul>
		
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>日期</th>
				<th>意向客户情况</th>
				<th>明天重点客户及情况</th>
				<th>联系方式</th>
				<shiro:hasPermission name="bg:bgDaily:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bgDaily">
			<tr>
				<td><a href="${ctx}/bg/bgDaily/form?id=${bgDaily.id}">
					<fmt:formatDate value="${bgDaily.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${bgDaily.riDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				   ${not empty bgDaily.bgDailyCustomerList ?bgDaily.bgDailyCustomerList[0].customerRemark:''}
				</td>
				<td>
					${bgDaily.tomorrowPlan}
				</td>
				<td>
					<c:forEach items="${bgDaily.bgDailyContacterList}" var="contacter">
					 ${contacter.name}/${contacter.mobileNumber}
					 </br>
					</c:forEach>
				</td>
				<shiro:hasPermission name="bg:bgDaily:edit"><td>
    				<a href="${ctx}/bg/bgDaily/form?id=${bgDaily.id}">修改</a>
					<%--<a href="${ctx}/bg/bgDaily/delete?id=${bgDaily.id}" onclick="return confirmx('确认要删除该日报吗？', this.href)">删除</a>
				--%></td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>