<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>表单列表</title>
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
						placeholder="根据表单名称，描述查找..." />
				</div>
				<a class="layui-btn " lay-submit lay-filter="search"
					data-type="reload">搜索</a>
			</div>

			<div class="layui-inline">
				<a class="layui-btn add-btn"
					data-url="${ctx }/question/toAddQuestion" data-title="发表新问题"
					style="background-color: #5FB878"> <i class="layui-icon">&#xe608;</i>发表新问题
				</a>
			</div>
		</form>
	</blockquote>

	<table class="layui-table"
		lay-data="{height:515, url:'${ctx }/question/list', page:true, id:'list-table'}"
		lay-filter="list">
		<thead>
			<tr>
				<th lay-data="{field:'questionId', width:50, sort: true}">ID</th>
				<th lay-data="{field:'departmentName', width:180}">院系</th>
				<th lay-data="{field:'username', width:120}">发布人</th>
				<th lay-data="{field:'title', width:350}">标题</th>
				<th
					lay-data="{field:'createtime',width:180 ,templet:'#createTimeTpl'}">发布时间</th>
				<th
					lay-data="{width:150, align:'center', toolbar: '#listBar', width:150}">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/html" id="createTimeTpl">
	<div>{{layui.util.toDateString(d.createtime, 'yyyy-MM-dd HH:mm:ss')}}</div>
	</script>
	<script type="text/html" id="deadlineTpl">
	<div>{{layui.util.toDateString(d.createtime, 'yyyy-MM-dd HH:mm:ss')}}</div>
	</script>

	<!--操作-->
	<script type="text/html" id="listBar">
		<a  class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
		
	</script>

	<script src="${ctx}/layui/layui.js"></script>

	<script>
		layui.use([ 'form', 'layer', 'laydate', 'table', 'laytpl', 'util' ], function() {
			var form = layui.form, layer = layui.layer, $ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table, util = layui.util;

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
				var id = data.questionId;
				if (layEvent === 'detail') { //删除
					var url = "${ ctx}/question/detail/" + id;
					var title = "问题详情";
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