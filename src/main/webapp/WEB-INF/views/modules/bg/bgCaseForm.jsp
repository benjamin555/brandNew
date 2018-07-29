<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function openDetail(url){
		top.$.jBox.open("iframe:"+url, "选择${title}", 1000, 500, {buttons:{"确定":"ok"}, submit:function(v, h, f){
				return true;
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
		
		}
		$(document).ready(function() {
		    $("#fixFee,#agentFee,#receiveFee").change(function(){
				var fixFee = $("#fixFee").val();
				var agentFee = $("#agentFee").val();
				var receiveFee = $("#receiveFee").val();
                fixFee = (isNaN(fixFee)?0:Number(fixFee));
                agentFee = (isNaN(agentFee)?0:Number(agentFee));
                receiveFee = isNaN(receiveFee)?0:Number(receiveFee);
				var rest = fixFee + agentFee -receiveFee;
				$("#phoneManId").val(rest);

			});
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bg/bgCase/">案件列表</a></li>
		<li class="active"><a href="${ctx}/bg/bgCase/form?id=${bgCase.id}">案件<shiro:hasPermission name="bg:bgCase:edit">${not empty bgCase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bg:bgCase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bgCase" action="${ctx}/bg/bgCase/save" method="post" class="form-horizontal disableCopy">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">立案日期：</label>
			<div class="controls">
				<input name="lianDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgCase.lianDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专利/商标名称：</label>
			<div class="controls">
				<form:input path="itemName" htmlEscape="false" maxlength="60" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册号：</label>
			<div class="controls">
				<form:input path="regNo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理事项：</label>
			<div class="controls">
				<form:input path="delegateItem" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规费：</label>
			<div class="controls">
				<form:input path="fixFee"  htmlEscape="false"  class="input-xlarge number"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理费：</label>
			<div class="controls">
				<form:input path="agentFee" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已收金额：</label>
			<div class="controls">
				<form:input path="receiveFee" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">欠费栏：</label>
			<div class="controls">
				<form:input path="phoneManId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务员：</label>
			<div class="controls">
				<form:input path="businessManId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户：</label>
			<div class="controls">
<sys:listSelect id="customer" name="customerId" value="${bgCase.customerId }" labelName="customer" labelValue="${bgCase.customer.name}"
					title="客户" url="/bg/bgCustomer/listHelp?bgCustomer.name=${bgCase.customer.name}" cssClass="required"/>
					
					<button class="btn-info" type="button" onclick="openDetail('${ctx}/bg/bgCustomer/detail?id=${bgCase.customerId }');">查看明细</button>	
							</div>
				
		</div>
		<div class="control-group">
			<label class="control-label">提成月份：</label>
			<div class="controls">
				<form:input path="feedbackMonth" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:select path="contacterId" >
				</form:select>
				<form:input path="contacterId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		--%><div class="control-group">
			<label class="control-label">国家受通时间：</label>
			<div class="controls">
				<input name="acceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgCase.acceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">驳回时间：</label>
			<div class="controls">
				<input name="rejectDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgCase.rejectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拿证时间：</label>
			<div class="controls">
				<input name="certificateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgCase.certificateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">跟进记录：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<th>描述</th>
						<th>提醒时间</th>
					</tr>
					</thead>
					<tbody >
					<c:forEach var="item" items="${bgCase.bgCaseFollowList}">
						<tr>
							<td>${item.descrip}</td>
							<td><fmt:formatDate value="${item.remindTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>

					</c:forEach>

					</tbody>
					<tfoot>
					</tfoot>
				</table>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bg:bgCase:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>