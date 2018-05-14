<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级表单</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

</head>
<body>
	<br>
	<form class="layui-form">

		<input type="text" name="classsId" value="${classs.classId }"
			hidden=hidden>

		<div class="layui-form-item">
			<label class="layui-form-label">选择框</label>
			<div class="layui-input-block">
				<select name="departmentId" lay-verify="required">
					<option value=""></option>
					<c:forEach var="d" items="${departments }">
						<c:if test="${d.departmentId == classs.departmentId }">
							<option value="${d.departmentId }" selected="selected">${d.name }</option>
						</c:if>
						<c:if test="${d.departmentId != classs.departmentId }">
							<option value="${d.departmentId }">${d.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">专业</label>
			<div class="layui-input-block">
				<input type="text" name="profession" required lay-verify="required"
					placeholder="请输入专业名称" autocomplete="off" class="layui-input"
					value="${classs.profession }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">年级</label>
			<div class="layui-input-block">
				<input type="number" name="grade" required lay-verify="required"
					placeholder="请输入年级" autocomplete="off" class="layui-input"
					value="${classs.grade }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">班级</label>
			<div class="layui-input-block">
				<input type="number" name="classNumber" required
					lay-verify="required" placeholder="请输入班级" autocomplete="off"
					class="layui-input" value="${classs.classNumber }">
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
					url : "${ctx}/admin/classspost",
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