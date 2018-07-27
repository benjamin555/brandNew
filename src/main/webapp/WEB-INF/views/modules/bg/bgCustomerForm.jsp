<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
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
		<li><a href="${ctx}/bg/bgCustomer/">客户列表</a></li>
		<li class="active"><a href="${ctx}/bg/bgCustomer/form?id=${bgCustomer.id}">客户<shiro:hasPermission name="bg:bgCustomer:edit">${not empty bgCustomer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bg:bgCustomer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bgCustomer" action="${ctx}/bg/bgCustomer/save" method="post" class="form-horizontal disableCopy">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">联系人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>备注</th>
								<th>联系人姓名</th>
								<th>手机号码</th>
								<th>固定电话号码</th>
								<th>传真号码</th>
									<shiro:hasPermission name="bg:bgCustomer:edit">
									<th width="10">&nbsp;</th>
									</shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="bgContacterList">
						</tbody>
						<shiro:hasPermission name="bg:bgCustomer:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#bgContacterList', bgContacterRowIdx, bgContacterTpl);bgContacterRowIdx = bgContacterRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="bgContacterTpl">//<!--
						<tr id="bgContacterList{{idx}}">
							<td class="hide">
								<input id="bgContacterList{{idx}}_id" name="bgContacterList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="bgContacterList{{idx}}_delFlag" name="bgContacterList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="bgContacterList{{idx}}_remarks" name="bgContacterList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="bgContacterList{{idx}}_name" name="bgContacterList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="bgContacterList{{idx}}_mobileNumber" name="bgContacterList[{{idx}}].mobileNumber" type="text" value="{{row.mobileNumber}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="bgContacterList{{idx}}_fixedPhoneNumber" name="bgContacterList[{{idx}}].fixedPhoneNumber" type="text" value="{{row.fixedPhoneNumber}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="bgContacterList{{idx}}_faxNumber" name="bgContacterList[{{idx}}].faxNumber" type="text" value="{{row.faxNumber}}" maxlength="20" class="input-small "/>
							</td>

							<shiro:hasPermission name="bg:bgCustomer:edit">
									<td class="text-center" width="10">
										{{#delBtn}}<span class="close" onclick="delRow(this, '#bgContacterList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
									</td>
									</shiro:hasPermission>

						</tr>//-->
					</script>

					<script type="text/template" id="bgContacterTplReadOnly">//<!--
						<tr id="bgContacterList{{idx}}">
							<td class="hide">
								<input id="bgContacterList{{idx}}_id" name="bgContacterList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="bgContacterList{{idx}}_delFlag" name="bgContacterList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea readonly id="bgContacterList{{idx}}_remarks" name="bgContacterList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small readonly">{{row.remarks}}</textarea>
							</td>
							<td>
								<input readonly id="bgContacterList{{idx}}_name" name="bgContacterList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small readonly"/>
							</td>
							<td>
								<input readonly id="bgContacterList{{idx}}_mobileNumber" name="bgContacterList[{{idx}}].mobileNumber" type="text" value="{{row.mobileNumber}}" maxlength="20" class="input-small readonly"/>
							</td>
							<td>
								<input readonly id="bgContacterList{{idx}}_fixedPhoneNumber" name="bgContacterList[{{idx}}].fixedPhoneNumber" type="text" value="{{row.fixedPhoneNumber}}" maxlength="20" class="input-small readonly"/>
							</td>
							<td>
								<input readonly id="bgContacterList{{idx}}_faxNumber" name="bgContacterList[{{idx}}].faxNumber" type="text" value="{{row.faxNumber}}" maxlength="20" class="input-small "/>
							</td>

							<shiro:hasPermission name="bg:bgCustomer:edit">
									<td class="text-center" width="10">
									</td>
									</shiro:hasPermission>

						</tr>//-->
					</script>
					<script type="text/javascript">
						var bgContacterRowIdx = 0, bgContacterTpl = $("#bgContacterTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						var bgContacterTplReadOnly = $("#bgContacterTplReadOnly").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"")
						$(document).ready(function() {
							var data = ${fns:toJson(bgCustomer.bgContacterList)};
							for (var i=0; i<data.length; i++){
							    if(data[i].id){
                                    addRow('#bgContacterList', bgContacterRowIdx, bgContacterTplReadOnly, data[i]);
								}else{
                                    addRow('#bgContacterList', bgContacterRowIdx, bgContacterTpl, data[i]);
								}
								bgContacterRowIdx = bgContacterRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="bg:bgCustomer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>