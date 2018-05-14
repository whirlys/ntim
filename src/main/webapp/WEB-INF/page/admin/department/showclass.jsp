<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>classs</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<table class="layui-table">
		<colgroup>
			<col>
			<col>
			<col>
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>学院</th>
				<th>专业</th>
				<th>年级</th>
				<th>班级</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${classlist }">
				<tr>
					<td>${c.department.name }</td>
					<td>${c.profession }</td>
					<td>${c.grade }</td>
					<td>${c.classNumber }</td>
				<tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>