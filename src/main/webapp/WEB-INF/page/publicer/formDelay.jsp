<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<title>Insert title here</title>
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">表单标题</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" disabled
					style="cursor: not-allowed !important;" value="${form.formName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">截止日期</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" disabled
					style="cursor: not-allowed !important;"
					value="${form.formatDeadline }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新截止日期</label>
			<div class="layui-input-block">
				<input type="text" name="deadline" class="layui-input" id="deadline"
					required lay-verify="required">
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
		//Demo
		layui.use([ 'laydate', 'form', 'element', 'jquery', 'layer' ], function() {
			var form = layui.form, element = layui.element, laydate = layui.laydate, $ = layui.jquery, layer = layui.layer;

			laydate.render({
				elem : '#deadline' //指定元素
				,
				type : 'datetime'
			});

			//监听提交
			form.on('submit(formDemo)', function(data) {
				$.ajax({
					url : "${ctx}/publicer/postDelay/${form.formId}",
					data : JSON.stringify(data.field),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						layer.msg('截止时间已修改');
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