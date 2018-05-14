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
	<table class="layui-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>学号</th>
				<th>性别</th>
				<th>手机号码</th>
				<th>职务</th>
				<th>家庭住址</th>
				<th>上次登录时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students }" var="s">
				<tr>
					<td>${s.studentId }</td>
					<td>${s.username }</td>
					<td>${s.account }</td>
					<td><c:if test="${s.sex == true }">男</c:if> <c:if
							test="${s.sex == false }">女</c:if></td>
					<td>${s.longPhone }</td>
					<td>${s.duty }</td>
					<td>${s.homeAddress }</td>
					<td>${s.lastLogin }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="${ctx}/layui/layui.js"></script>
</body>
</html>