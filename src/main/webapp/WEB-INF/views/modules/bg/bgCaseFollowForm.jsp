<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案例进度管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		<li class="active"><a href="${ctx}/bg/bgCaseFollow/form?id=${bgCaseFollow.id}">案例进度<shiro:hasPermission name="bg:bgCaseFollow:edit">${not empty bgCaseFollow.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bg:bgCaseFollow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bgCaseFollow" action="${ctx}/bg/bgCaseFollow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="LH_VIEW_OVERRIDE" value="${param.returnUrl}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="descrip" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提醒时间：</label>
			<div class="controls">
				<input name="remindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgCaseFollow.remindTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</div>
		</div>
		<form:hidden path="caseId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
		<div class="form-actions">
			<shiro:hasPermission name="bg:bgCaseFollow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>