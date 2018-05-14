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
<title>发布者表单</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

</head>
<body>
	<br>
	<form class="layui-form" data-action="${ctx }/admin/publicer/form">

		<input type="text" name="publicerId" value="${form.publicerId }"
			hidden=hidden>
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" name="username" lay-verify="required"
					autocomplete="off" placeholder="请输入姓名" class="layui-input"
					value="${form.username }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">编号</label>
			<div class="layui-input-block">
				<input type="text" name="account" lay-verify="required"
					autocomplete="off" placeholder="请输入编号" class="layui-input"
					value="${form.account }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" name="password" lay-verify="required"
					autocomplete="off" placeholder="请输入密码" class="layui-input"
					value="${form.password }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="email" name="email" lay-verify="required"
					autocomplete="off" placeholder="请输入邮箱" class="layui-input"
					value="${form.email }">
			</div>
		</div>
		<div class=" layui-form-item">
			<label class="layui-form-label">院系</label>
			<div class="layui-input-block">
				<select name="departmentId" lay-verify="required">
					<option value=""></option>
					<c:forEach var="d" items="${departments }">
						<c:if test="${d.departmentId ==  form.departmentId}">
							<option value="${d.departmentId }" selected="selected">${d.name }</option>
						</c:if>
						<c:if test="${d.departmentId !=  form.departmentId}">
							<option value="${d.departmentId }">${d.name }</option>
						</c:if>

					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">头衔</label>
			<div class="layui-input-block">
				<input type="text" name="title" lay-verify="required"
					autocomplete="off" placeholder="请输入头衔" class="layui-input"
					value="${form.title }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">办公室</label>
			<div class="layui-input-block">
				<input type="text" name="office" lay-verify="required"
					autocomplete="off" placeholder="请输入办公室" class="layui-input"
					value="${form.office }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">职务</label>
			<div class="layui-input-block">
				<select name="duty" lay-verify="required">
					<option value=""></option>
					<c:forTokens items="学院院长,辅导员,教学秘书,班主任,学生会,社团,班长" delims=","
						var="item">
						<c:if test="${form.duty == item }">
							<option value="${item }" selected="selected">${item }</option>
						</c:if>
						<c:if test="${form.duty != item }">
							<option value="${item }">${item }</option>
						</c:if>
					</c:forTokens>
				</select>
			</div>
		</div>
		<div class="layui-form-item" pane="">
			<label class="layui-form-label">简介</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" name="introduction"
					class="layui-textarea">${form.introduction }</textarea>
			</div>
		</div>
		<div class="layui-form-item" pane="">
			<label class="layui-form-label">开关-开</label>
			<div class="layui-input-block">
				<input type="checkbox" checked="" name="isActive" lay-skin="switch"
					title="开关" value="${form.isActive }" lay-text="ON|OFF">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="data-form">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script src="${ctx}/layui/layui.js"></script>
	<script src="${ctx }/js/common/form.js"></script>
</body>
</html>