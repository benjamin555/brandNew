<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日报管理</title>
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
		
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bg/bgDaily/">日报列表</a></li>
		<li class="active"><a href="${ctx}/bg/bgDaily/form?id=${bgDaily.id}">日报<shiro:hasPermission name="bg:bgDaily:edit">${not empty bgDaily.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bg:bgDaily:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bgDaily" action="${ctx}/bg/bgDaily/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<input name="riDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bgDaily.riDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">创建人：</label>
			<div class="controls">
				<input type="text" readonly="readonly" 
					value="${bgDaily.createBy.name}"
					/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">意向客户及情况：</label>
			<div class="controls">
				<form:textarea path="todayRemark" htmlEscape="false" rows="6" maxlength="1000" cssStyle="width: 95%;"   class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">明天重点客户及情况：</label>
			<div class="controls">
				<form:textarea path="tomorrowPlan" htmlEscape="false" rows="6" maxlength="1000" cssStyle="width: 95%;" class="input-xxlarge "/>
			</div>
		</div>
		
		
		<div class="control-group">
				<label class="control-label">联系人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="width:400px;">备注</th>
								<th>联系人姓名</th>
								<th>手机号码</th>
								<th>商标</th>
								<th>类型</th>
								<shiro:hasPermission name="bg:bgDaily:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="bgDailyContacterList">
						</tbody>
						<shiro:hasPermission name="bg:bgDaily:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#bgDailyContacterList', bgContacterRowIdx, bgContacterTpl);bgContacterRowIdx = bgContacterRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="bgContacterTpl">//<!--
						<tr id="bgDailyContacterList{{idx}}">
							<td class="hide">
								<input id="bgDailyContacterList{{idx}}_id" name="bgDailyContacterList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="bgDailyContacterList{{idx}}_delFlag" name="bgDailyContacterList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="bgDailyContacterList{{idx}}_remarks" name="bgDailyContacterList[{{idx}}].remarks" rows="4" style="width:90%;"  maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="bgDailyContacterList{{idx}}_name" name="bgDailyContacterList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="bgDailyContacterList{{idx}}_mobileNumber" name="bgDailyContacterList[{{idx}}].mobileNumber" type="text" value="{{row.mobileNumber}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="bgDailyContacterList{{idx}}_brand" name="bgDailyContacterList[{{idx}}].brand" type="text" value="{{row.brand}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="bgDailyContacterList{{idx}}_typ" name="bgDailyContacterList[{{idx}}].typ" type="text" value="{{row.typ}}" maxlength="20" class="input-small "/>
							</td>
							<shiro:hasPermission name="bg:bg:bgDaily:edit:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#bgDailyContacterList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					
					
					<script type="text/javascript">
						var bgContacterRowIdx = 0, bgContacterTpl = $("#bgContacterTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(bgDaily.bgDailyContacterList)};
							for (var i=0; i<data.length; i++){
								addRow('#bgDailyContacterList', bgContacterRowIdx, bgContacterTpl, data[i]);
								bgContacterRowIdx = bgContacterRowIdx + 1;
							}
						});
					</script>
					
				</div>
			</div>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="bg:bgDaily:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>