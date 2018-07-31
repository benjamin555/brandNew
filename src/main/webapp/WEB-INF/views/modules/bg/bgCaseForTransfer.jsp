<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件业务员调整</title>
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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bgCase" action="${ctx}/bg/bgCase/transfer" method="post" class="form-horizontal ">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">原业务员：</label>
			<div class="controls">
				<form:input path="oldBusinessMan" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新业务员：</label>
			<div class="controls">
				<form:input path="newBusinessMan" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bg:bgCase:transfer"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>