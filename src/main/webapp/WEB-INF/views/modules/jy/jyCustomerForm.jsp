<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户进度管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var username = '${fns:getUser().name}';
		var userId = '${fns:getUser().id}';
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
			if(!row){
				row = {follower:{id:userId,name:username}}
			}
			var delBtn = true;
			if(row.id){
				delBtn =false;
			}
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: delBtn, row: row
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
		<li class="${active eq 'listSelf'?'active':''}"><a href="${ctx}/jy/jyCustomer/listSelf">客户进度列表</a></li>
		<shiro:hasPermission name="jy:jyCustomer:superView"><li class="${active eq 'list'?'active':''}"><a href="${ctx}/jy/jyCustomer/list">总客户进度列表</a></li></shiro:hasPermission>
	
		<li class="active"><a href="${ctx}/jy/jyCustomer/form?id=${jyCustomer.id}">客户进度<shiro:hasPermission name="jy:jyCustomer:edit">${not empty jyCustomer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jy:jyCustomer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="jyCustomer" action="${ctx}/jy/jyCustomer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户类型：</label>
			<div class="controls">
				<form:select path="typ" class="input-xlarge required" items="${ fns:getDictList('jy_follwer_type') }" itemLabel="label" itemValue="value"></form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理项目：</label>
			<div class="controls">
				<form:input path="agent" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户级别：</label>
			<div class="controls">
				<form:select path="level" class="input-xlarge required" items="${ fns:getDictList('jy_follwer_level') }" itemLabel="label" itemValue="value"></form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前跟进人：</label>
			<div class="controls">
			
				<sys:treeselect id="currentFollowerId" name="currentFollowerId"  labelName="user.name" labelValue="${jyCustomer.currentFollower.name}"  value="${jyCustomer.currentFollower.id}"
							title="当前跟进人" url="/sys/user/treeDataByCode?officeCode=1207550101" cssClass="required recipient" cssStyle="width:150px" 
							allowClear="true" notAllowSelectParent="true" smallBtn="false"/>
			
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">京远联系人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>备注</th>
								<th>名称</th>
								<th>电话号码</th>
								<th>QQ或微信</th>
								<shiro:hasPermission name="jy:jyCustomer:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="jyContacterList">
						</tbody>
						<shiro:hasPermission name="jy:jyCustomer:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#jyContacterList', jyContacterRowIdx, jyContacterTpl);jyContacterRowIdx = jyContacterRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="jyContacterTpl">//<!--
						<tr id="jyContacterList{{idx}}">
							<td class="hide">
								<input id="jyContacterList{{idx}}_id" name="jyContacterList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="jyContacterList{{idx}}_delFlag" name="jyContacterList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="jyContacterList{{idx}}_remarks" name="jyContacterList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="jyContacterList{{idx}}_name" name="jyContacterList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="jyContacterList{{idx}}_phoneNumber" name="jyContacterList[{{idx}}].phoneNumber" type="text" value="{{row.phoneNumber}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="jyContacterList{{idx}}_qqowx" name="jyContacterList[{{idx}}].qqowx" type="text" value="{{row.qqowx}}" maxlength="32" class="input-small "/>
							</td>
							<shiro:hasPermission name="jy:jyCustomer:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#jyContacterList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var jyContacterRowIdx = 0, jyContacterTpl = $("#jyContacterTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(jyCustomer.jyContacterList)};
							for (var i=0; i<data.length; i++){
								addRow('#jyContacterList', jyContacterRowIdx, jyContacterTpl, data[i]);
								jyContacterRowIdx = jyContacterRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">京远客户跟进：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>备注</th>
								<th>跟进时间</th>
								<th>跟进内容</th>
								<th>跟进人</th>
								<shiro:hasPermission name="jy:jyCustomer:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="jyCustomerFollowList">
						</tbody>
						<shiro:hasPermission name="jy:jyCustomer:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#jyCustomerFollowList', jyCustomerFollowRowIdx, jyCustomerFollowTpl);jyCustomerFollowRowIdx = jyCustomerFollowRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="jyCustomerFollowTpl">//<!--
						<tr id="jyCustomerFollowList{{idx}}">
							<td class="hide">
								<input id="jyCustomerFollowList{{idx}}_id" name="jyCustomerFollowList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="jyCustomerFollowList{{idx}}_delFlag" name="jyCustomerFollowList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="jyCustomerFollowList{{idx}}_followerId" name="jyCustomerFollowList[{{idx}}].follower.id" type="text" value="{{row.follower.id}}" maxlength="64" class="input-small "   />
							</td>
							<td>
								<textarea id="jyCustomerFollowList{{idx}}_remarks" name="jyCustomerFollowList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="jyCustomerFollowList{{idx}}_dat" name="jyCustomerFollowList[{{idx}}].dat" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="{{row.dat}}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
							<td>
								<textarea id="jyCustomerFollowList{{idx}}_followContent" name="jyCustomerFollowList[{{idx}}].followContent" rows="4"  maxlength="400" class="input-small ">{{row.followContent}}</textarea>
							</td>
							<td>
								<input id="jyCustomerFollowList{{idx}}_followerName" name="jyCustomerFollowList[{{idx}}].follower.name" type="text" value="{{row.follower.name}}" maxlength="64" class="input-small "  readonly />
							</td>
							<shiro:hasPermission name="jy:jyCustomer:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#jyCustomerFollowList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var jyCustomerFollowRowIdx = 0, jyCustomerFollowTpl = $("#jyCustomerFollowTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(jyCustomer.jyCustomerFollowList)};
							for (var i=0; i<data.length; i++){
								addRow('#jyCustomerFollowList', jyCustomerFollowRowIdx, jyCustomerFollowTpl, data[i]);
								jyCustomerFollowRowIdx = jyCustomerFollowRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="jy:jyCustomer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>