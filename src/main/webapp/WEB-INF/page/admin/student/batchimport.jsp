<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>批量导入学生</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body style="padding: 20px">
	<div>
		<button type="button" class="layui-btn" id="upload">
			<i class="layui-icon">&#xe67c;</i>选择Excel文件
		</button>
		<span id="uploadMessage"></span>
	</div>
	<table class="layui-table">

		<thead>
			<tr>
				<th>姓名</th>
				<th>学号</th>
				<th>学院</th>
				<th>年级</th>
				<th>专业</th>
				<th>班级号</th>
				<th>学生类型</th>
				<th>性别</th>
			</tr>
		</thead>
		<tbody id="tbody">

		</tbody>
	</table>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		layui.use([ 'upload', 'jquery' ], function() {
			var upload = layui.upload, $ = layui.jquery;
			//执行实例
			var uploadMessage = $("#uploadMessage");

			var uploadInst = upload.render({
				elem : '#upload' //绑定元素
				,
				url : '${ctx}/admin/excelUpload' //上传接口
				,
				accept : "file",
				exts : "xlsx|xls",
				done : function(res) {

					if (res.code == 100) {
						$("#uploadMessage").css("color", "none");
						$("#uploadMessage").css("color", "green");
						$("#uploadMessage").text(res.msg);
						$.each(res["map"]["studentvos"], function(index, item) {
							$("#tbody").append("<tr><td>" + item["username"] + "</td><td>" + item["account"] + "</td><td>" + item["departmentName"] + "</td><td>" + item["grade"] + "</td><td>" + item["profession"] + "</td><td>" + item["classNumber"] + "</td><td>" + item["studentType"] + "</td><td>" + item["sex"] + "</td></tr>");
						})
					} else {
						$("#uploadMessage").css("color", "none");
						$("#uploadMessage").css("color", "red");
					}

				},
				error : function() {
					//请求异常回调
				}
			});
		});
	</script>

</body>

</html>
