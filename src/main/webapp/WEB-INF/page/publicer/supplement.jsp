<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<title>添加补充说明</title>
</head>
<body>
	<form class="layui-form" action="" method="post">
		<textarea id="content" style="display: none;" name="content"></textarea>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<span style="color: orange; margin-left: 20px;">${message }</span>
			</div>
		</div>
	</form>
	<script src="${ctx}/layui/layui.js"></script>
	<script>
		layui.use([ 'layedit', 'form', 'layer' ], function() {
			var layedit = layui.layedit, form = layui.form;
			layedit.set({
				uploadImage : {
					url : '${ctx}/upload/layedit' //接口url

				}
			});
			layedit.build('content'); //建立编辑器

		});
	</script>
</body>
</html>