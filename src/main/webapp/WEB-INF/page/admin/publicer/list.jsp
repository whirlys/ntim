<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>发布者列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body class="childrenBody">

	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class=" layui-inline">
				<label class="layui-form-label">院系</label>
				<div class="layui-input-block">
					<select name="departmentId">
						<option value=""></option>
						<c:forEach var="d" items="${departments }">
							<option value="${d.departmentId }">${d.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="q" class="layui-input searchVal"
						placeholder="根据姓名、编号或头衔查找..." />
				</div>
				<a class="layui-btn " lay-submit lay-filter="search"
					data-type="reload">搜索</a>
			</div>

			<div class="layui-inline">
				<a class="layui-btn add-btn" data-url="${ctx }/admin/publicer/toAdd"
					data-title="添加发布者" style="background-color: #5FB878"> <i
					class="layui-icon">&#xe608;</i>添加
				</a>
			</div>
		</form>


	</blockquote>


	<table class="layui-table"
		lay-data="{height:455, url:'${ctx }/admin/publicer/list', page:true, id:'list-table'}"
		lay-filter="list">
		<thead>
			<tr>
				<th lay-data="{field:'publicerId', width:50, sort: true}">ID</th>
				<th lay-data="{field:'username', width:120}">姓名</th>
				<th lay-data="{field:'account', width:120, sort: true}">编号</th>
				<th lay-data="{field:'departmentName',width:150}">学院</th>
				<th lay-data="{field:'title',width:120}">头衔</th>
				<th lay-data="{field:'office', sort: true,width:120}">办公室</th>
				<th lay-data="{field:'duty', sort: true,width:120}">职务</th>
				<th
					lay-data="{width:150, align:'center', toolbar: '#listBar', width:150}">操作</th>
			</tr>
		</thead>
	</table>

	<!--操作-->
	<script type="text/html" id="listBar">
		<a  class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a  class="layui-btn layui-btn-xs layui-btn-normal" lay-event="managed">班级</a>
	</script>

	<script src="${ctx}/layui/layui.js"></script>

	<script>
		layui.use([ 'form', 'layer', 'laydate', 'table', 'laytpl' ], function() {
			var form = layui.form, layer = layui.layer, $ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table;

			// 监听提交
			form.on('submit(search)', function(data) {
				table.reload('list-table', {
					where : data.field
				});
			});

			// 添加
			$(".add-btn").click(function() {
				var url = $(this).data("url");
				var title = $(this).data("title");
				var index = layui.layer.open({
					title : title,
					type : 2,
					content : url,
					success : function(layero, index) {

					}
				})
				layui.layer.full(index);
				//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				})
			});

			// 监听工具条 编辑
			table.on('tool(list)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				var tr = obj.tr;
				var id = data.publicerId;
				if (layEvent === 'del') { //删除
					layer.confirm('真的删除行么', function(index) {
						obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
						layer.close(index);
						//向服务端发送删除指令
					});
				} else if (layEvent === 'edit') { //编辑

					var url = "${ ctx}/admin/publicer/toEdit?publicerId=" + id;
					var title = "编辑发布者";
					var index = layui.layer.open({
						title : title,
						type : 2,
						content : url,
						success : function(layero, index) {
						}
					})
					layui.layer.full(index);
					//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
					$(window).on("resize", function() {
						layui.layer.full(index);
					})
				} else if (layEvent == "managed") {
					// 管理班级
					var url = "${ctx}/admin/publicer/managed?publicerId=" + id;
					var title = "发布者班级管理";
					var index = layui.layer.open({
						title : title,
						type : 2,
						content : url,
						success : function(layero, index) {

						}
					})
					layui.layer.full(index);
					//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
					$(window).on("resize", function() {
						layui.layer.full(index);
					})
				}
			});

		})
	</script>
</body>
</html>