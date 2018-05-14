<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布通知</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<form class="layui-form" action="${ctx }/publicer/addForm"
		method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">表单名称</label>
			<div class="layui-input-block">
				<input type="text" name="formName" required lay-verify="required"
					placeholder="请输入表单名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">表单描述</label>
			<div class="layui-input-block">
				<textarea name="description" placeholder="请输入内容"
					class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">截止日期</label>
			<div class="layui-input-block">
				<input type="text" name="deadline" class="layui-input" id="deadline"
					required lay-verify="required">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">表单接收人</label>
			<div class="layui-input-block">
				<table class="layui-table">
					<thead>
						<tr>
							<th>班级</th>
							<th>选择表单接收人</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${studentGroups }" var="group">
							<tr>
								<td><input type="checkbox" class="groupbox"
									title="${group.groupname }" lay-skin="primary"
									lay-filter="group" id="${group.id }"></td>
								<td><c:forEach items="${group.list }" var="item">
										<input type="checkbox" title="${item.username }"
											value="student" lay-filter="student" id="${item.id }"
											gid="${group.id }">
									</c:forEach></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">表单字段</label>
			<div class="layui-input-block">
				<div class="layui-collapse collapses"></div>

				<a class="layui-btn layui-btn-normal addFormItem"
					href="javascript:;">新增一项</a>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		layui.use([ 'laydate', 'layer', 'jquery', 'form', 'element' ], function() {
			var laydate = layui.laydate;
			var layer = layui.layer, $ = layui.jquery, form = layui.form, element = layui.element;

			laydate.render({
				elem : '#deadline' //指定元素
				,
				type : 'datetime'
			});

			form.on('checkbox(group)', function(data) {
				$("input[type='checkbox'][gid='" + data.elem.id + "']").each(function() {
					this.checked = data.elem.checked;
				});
				form.render('checkbox');
			});
			form.on('checkbox(student)', function(data) {
				form.render('checkbox');
			});

			$(".addFormItem").click(
					function() {
						var html = '<div class="layui-colla-item"><h2 class="layui-colla-title">添加字段</h2><div class="layui-colla-content layui-show"><div class="layui-form-item"><label class="layui-form-label">名称</label><div class="layui-input-inline"><input type="text"name="name"required lay-verify="required"placeholder="请输入名称"autocomplete="off"class="layui-input"></div><label class="layui-form-label">输入框类型</label><div class="layui-input-inline"><select name="type"lay-verify="required"><option value="text"checked>文本框</option>'
								+ '<option value="password">密码框</option><option value="textarea">文本域</option><option value="checkbox">复选框</option><option value="radio">单选框</option><option value="select">下拉框</option></select></div><label class="layui-form-label">是否必填</label><div class="layui-input-inline"><input type="checkbox"name="required"value="1"lay-skin="switch"lay-text="是|否"></div><label class="layui-form-label">默认值</label><div class="layui-input-inline"><input type="text"name="defaultValue"placeholder=""autocomplete="off"class="layui-input"></div></div></div></div>';

						$(".collapses").append(html);
						element.render("collapse");
						form.render();
					});

			form.on('submit(*)', function(data) {
				form.render();
				var ids = [];

				$("input[type='checkbox'][value='student']").each(function() {
					if (this.checked == true) {
						ids.push(this.id);
					}
				})
				if (ids.length == 0) {
					layer.alert("请选择接收人！", {
						offset : '150px'
					});
					return false;
				}
				if ($(".collapses").children().length == 0) {
					layer.alert("请添加表单字段！", {
						offset : '150px'
					});
					return false;
				}
				var fieldItems = [];
				var rank = 1;
				$.each($(".collapses").children(), function() {
					var fieldItem = {};
					fieldItem["name"] = $(this).find('input[name="name"]').val();
					if ($(this).find('input[type="checkbox"]').prop("checked") == true) {
						fieldItem["required"] = 1;
					} else {
						fieldItem["required"] = 0;
					}

					fieldItem["type"] = $(this).find('select[name="type"]').val();
					fieldItem["defaultValue"] = $(this).find('input[name="defaultValue"]').val();
					fieldItem["rank"] = rank;
					rank = rank + 1;
					fieldItems.push(fieldItem);
				})
				var fields = {};
				fields['receivers'] = ids;
				fields['formName'] = data.field.formName;
				fields['description'] = data.field.description;
				fields['deadline'] = data.field.deadline;
				fields['fieldItems'] = fieldItems;

				$.ajax({
					url : "${ctx}/publicer/addForm",
					data : JSON.stringify(fields),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data['code'] == 100) {
							layer.closeAll("iframe");
							//刷新父页面
							parent.location.reload();
						} else {
							layer.alert(JSON.stringify(data))
						}
					},
					error : function(xhr, textStatus) {

					},
				});

			});
		});
	</script>
</body>
</html>