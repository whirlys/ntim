<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院系表单</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

</head>
<body>
	<br>
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" required lay-verify="required"
					placeholder="请输入名称" autocomplete="off" class="layui-input"
					value="${department.name }">
			</div>
		</div>


		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="remark" placeholder="请输入内容" class="layui-textarea">${department.remark }</textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script src="${ctx}/layui/layui.js"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form, $ = layui.jquery;

			//监听提交
			form.on('submit(formDemo)', function(data) {

				$.ajax({
					url : "${ctx}/admin/departmentpost",
					data : data.field,
					method : "post",
					dataType : 'json',
					success : function(data) {
						if (data.code == 100) {
							layer.msg(data.msg);
							layer.closeAll("iframe");
							//刷新父页面
							parent.location.reload();
						} else if (data.code == 300) {
							layer.msg(data.map.error);
						} else {
							layer.msg(data.msg);
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