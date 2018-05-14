<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script src="${ctx}/layui/layui.js"></script>
<style type="text/css">
.login-box {
	margin-top: 100px;
	text-align: center;
}

.login-box h2 {
	margin-bottom: 10px;
	font-weight: 300;
	font-size: 30px;
	color: #000;
}

.login-box p {
	font-weight: 300;
	color: #999;
}

.login-form {
	padding: 50px;
	
}
</style>
</head>
<body>
	<div class="layui-container">
		<div class="login-box">
			<h2>登录</h2>
			<p>大学消息通知系统服务端的设计与实现</p>
		</div>
		<form class="layui-form login-form" method="post">

			<h2 style="color: red;">${errormsg }</h2>
			<div class="layui-form-item">
				<label class="layui-form-label">学号或编号</label>
				<div class="layui-input-block">
					<input type="text" name="account" placeholder="请输入学号或编号"
						autocomplete="off" class="layui-input"
						lay-verify="required|account" value="${account }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="password" name="password" autocomplete="off"
						class="layui-input" lay-verify="required|password">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>

	<script>
		layui.use([ 'form', 'jquery', 'layer' ], function() {
			var form = layui.form, $ = layui.jquery, layer = layui.layer;

			// 表单验证
			form.verify({
				account : function(value, item) { //value：表单的值、item：表单的DOM对象

					if (value.length != 12 && value.length != 8) {
						return "帐号长度应为 8 或 12";
					}
				}
			})

		});
	</script>
</body>
</html>