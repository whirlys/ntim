<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>院系列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${ctx}/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal add_btn">添加院系</a>
			</div>

		</blockquote>
	</form>
	<table class="layui-table">
	
		<thead>
			<tr>
				<th>ID</th>
				<th>名称</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${dlist }">
				<tr>
					<td>${d.departmentId }</td>
					<td>${d.name }</td>
					<td>${d.remark }</td>
					<td><a
						class="layui-btn layui-btn-xs layui-btn-danger showclass"
						data-id="${d.departmentId }">查看班级</a></td>
				<tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'form', 'layer', 'laydate', 'table', 'laytpl' ], function() {
			var form = layui.form, layer = parent.layer === undefined ? layui.layer : top.layer, $ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table;
			// 添加院系
			function addDepartment(edit) {
				var index = layui.layer.open({
					title : "添加院系",
					type : 2,
					content : "${ctx}/admin/departmentadd",
					success : function(layero, index) {
						var body = layui.layer.getChildFrame('body', index);

						setTimeout(function() {
							layui.layer.tips('点击此处返回学院列表', '.layui-layer-setwin .layui-layer-close', {
								tips : 3
							});
						}, 500)
					}
				})
				layui.layer.full(index);
				//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				})
			}

			// 添加院系 点击事件
			$(".add_btn").click(function() {
				addDepartment();
			})

			// 查看班级
			$(".showclass").click(function() {
				var id = $(this).data('id');
				var index = layui.layer.open({
					title : "查看班级",
					type : 2,
					content : "${ctx}/admin/showclass?departmentid=" + id,
					success : function(layero, index) {
						var body = layui.layer.getChildFrame('body', index);

						setTimeout(function() {
							layui.layer.tips('点击此处返回学院列表', '.layui-layer-setwin .layui-layer-close', {
								tips : 3
							});
						}, 500)
					}
				})
				layui.layer.full(index);
				//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				})
			})
		})
	</script>
</body>
</html>