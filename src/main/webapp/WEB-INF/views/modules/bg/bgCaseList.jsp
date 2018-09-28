<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			

		});
		function resetForm(){
			$("#searchForm").find(".ul-form input[type='text']").val(null);
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body >
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bg/bgCase/">单表列表</a></li>
		<shiro:hasPermission name="bg:bgCase:edit"><li><a href="${ctx}/bg/bgCase/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bgCase" action="${ctx}/bg/bgCase/" method="post" class="breadcrumb form-search disableCopy">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>立案日期：</label>
				<input name="lianDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgCase.lianDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>专利/商标名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>注册号：</label>
				<form:input path="regNo" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>欠费栏：</label>
				<form:input path="phoneManId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>业务员：</label>
				<form:input path="businessManId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户：</label>
					
				<sys:listSelect id="customer" name="customerId" value="${customerId }" labelName="customer" labelValue="${customer.name}"
					title="客户" url="/bg/bgCustomer/list" cssClass="required"/>	
			</li>
			<li><label>提成月份：</label>
				<form:input path="feedbackMonth" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>联系人：</label>
				<form:input path="contacter.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>国家受通时间：</label>
				<input name="acceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgCase.acceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>驳回时间：</label>
				<input name="rejectDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgCase.rejectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>拿证时间：</label>
				<input name="certificateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bgCase.certificateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false"  class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input  class="btn btn-primary" type="button" onclick="resetForm();" value="重置"/>
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed disableCopy">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注</th>
				<th>立案日期</th>
				<th>专利/商标名称</th>
				<th>注册号</th>
				<th>代理事项</th>
				<th>规费</th>
				<th>代理费</th>
				<th>已收金额</th>
				<th>欠费栏</th>
				<th>业务员</th>
				<th>客户</th>
				<th>提成月份</th>
				<th>联系人</th>
				<th>国家受通时间</th>
				<th>驳回时间</th>
				<th>拿证时间</th>
				<shiro:hasPermission name="bg:bgCase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bgCase">
			<tr>
				<td><a href="${ctx}/bg/bgCase/form?id=${bgCase.id}">
					<fmt:formatDate value="${bgCase.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bgCase.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bgCase.lianDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bgCase.itemName}
				</td>
				<td>
					${bgCase.regNo}
				</td>
				<td>
					${bgCase.delegateItem}
				</td>
				<td>
					${bgCase.fixFee}
				</td>
				<td>
					${bgCase.agentFee}
				</td>
				<td>
					${bgCase.receiveFee}
				</td>
				<td>
					${bgCase.phoneManId}
				</td>
				<td>
					${bgCase.businessManId}
				</td>
				<td>
					${bgCase.customer.name}
				</td>
				<td>
					${bgCase.feedbackMonth}
				</td>
				<td>
					${bgCase.contacters}
				</td>
				<td>
					<fmt:formatDate value="${bgCase.acceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bgCase.rejectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bgCase.certificateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
    				<shiro:hasPermission name="bg:bgCase:edit"><a href="${ctx}/bg/bgCase/form?id=${bgCase.id}">修改</a></shiro:hasPermission>
					<shiro:hasPermission name="bg:bgCase:del"><a href="${ctx}/bg/bgCase/delete?id=${bgCase.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a></shiro:hasPermission>
				
					<a href="${ctx}/bg/bgCaseFollow/form?caseId=${bgCase.id}">跟进</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>