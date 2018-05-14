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

	<form class="layui-form layui-form-pane" action="">
		<table class="layui-table">
			<thead>
				<tr>
					<th>选择接收的院系</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:forEach items="${departments }" var="d">
							<input type="checkbox" title="${d.name }" lay-skin="primary"
								lay-filter="department" id="${d.departmentId }">
						</c:forEach></td>
				</tr>
			</tbody>
		</table>
		<div class="layui-form-item">
			<label class="layui-form-label">重要等级</label>
			<div class="layui-input-inline">
				<select name="lever" lay-verify="required">
					<option value=""></option>
					<c:forEach var="lever" items="<%=NoticeLever.values()%>">
						<option value="${lever.lever }">${lever.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-inline">
				<select name="type" required lay-verify="required">
					<option value=""></option>
					<c:forEach var="item" items="<%=NoticeType.values()%>">
						<option value="${item.name }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" required lay-verify="required"
					placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<textarea id="content" style="display: none;" name="content"></textarea>
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
		layui.use([ 'layedit', 'layer', 'jquery', 'form' ], function() {
			var layedit = layui.layedit, layer = layui.layer, $ = layui.jquery, form = layui.form;

			layedit.set({
				uploadImage : {
					url : '${ctx}/upload/layedit' //接口url

				}
			});
			var contentIndex = layedit.build("content", {
				height : 500
			}); //建立编辑器

			form.on('checkbox(student)', function(data) {
				form.render('checkbox');
			});

			form.on('submit(*)', function(data) {
				var ids = [];
				$("input[type='checkbox']").each(function() {
					if (this.checked == true) {
						ids.push(this.id);
					}
				})
				if (ids.length == 0) {
					layer.alert("请选择院系！", {
						offset : '150px'
					});
					return false;
				}
				var fields = {};
				fields['receivers'] = ids;
				fields['lever'] = data.field.lever;
				fields['type'] = data.field.type;
				fields['title'] = data.field.title;
				fields['content'] = layedit.getContent(contentIndex);
				$.ajax({
					url : "${ctx}/admin/notice/add",
					data : fields,
					method : "post",
					dataType : 'json',
					traditional : true,
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
				return false;
			});
		});
	</script>
</body>
</html>