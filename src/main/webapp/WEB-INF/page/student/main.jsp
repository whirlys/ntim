<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看个人信息</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

<script src="${ctx}/layui/layui.js"></script>

</head>
<body>

	<div class="layui-row">
		<blockquote class="layui-elem-quote quoteBox">
			<form class="layui-form">
				<div class="layui-inline">
					<h2>最新教务处通知</h2>
				</div>
				<div class=" layui-inline">
					<label class="layui-form-label">类型</label>
					<div class="layui-input-block">
						<select name="type">
							<option value=""></option>
							<c:forEach var="item" items="${types }">
								<option value="${item }">${item }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="q" class="layui-input searchVal"
							placeholder="根据关键字查找通知标题.." />
					</div>
					<a class="layui-btn " lay-submit lay-filter="search-jwc"
						data-type="reload">搜索</a>
				</div>
			</form>
		</blockquote>


		<table class="layui-table"
			lay-data="{skin:'nob',height:315, url:'${ctx }/student/jwcposts', page:true, id:'jwc-list-table',limit:5,limits:[5,10,15]}"
			lay-filter="list">
			<thead>
				<tr>
					<th lay-data="{field:'jwcpostId', width:80, sort: true}">ID</th>
					<th lay-data="{field:'title', width:480}">通知标题</th>
					<th lay-data="{field:'remarks', width:200, sort: true}">描述</th>
					<th lay-data="{field:'url',width:150 ,templet:'#urlTpl'}">url</th>
					<th
						lay-data="{field:'crawltime',width:200 ,templet:'#crawltimeTpl'}">发布时间</th>
				</tr>
			</thead>
		</table>


		<div class="layui-row">
			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form">
					<div class="layui-inline">
						<h2>最新公共文件</h2>
					</div>
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" name="q" class="layui-input searchVal"
								placeholder="根据文件名称，描述查找..." />
						</div>
						<a class="layui-btn " lay-submit lay-filter="search"
							data-type="reload">搜索</a>
					</div>
				</form>
			</blockquote>


			<table class="layui-table"
				lay-data="{skin:'nob',height:315, url:'${ctx }/publicer/files', page:true, id:'list-table',limit:5,limits:[5,10,15]}"
				lay-filter="list">
				<thead>
					<tr>
						<th lay-data="{field:'fileId', width:80, sort: true}">ID</th>
						<th lay-data="{field:'filename', width:480}">文件名称</th>
						<th lay-data="{field:'remarks', width:250, sort: true}">描述</th>
						<th lay-data="{field:'url',width:150 ,templet:'#urlTpl'}">URL</th>
						<th
							lay-data="{field:'createtime',width:200 ,templet:'#createtimeTpl'}">上传时间</th>
					</tr>
				</thead>
			</table>

			<script>
				layui.use([ 'form', 'layer', 'table', 'laytpl', 'util' ], function() {
					var form = layui.form, layer = layui.layer, $ = layui.jquery, laytpl = layui.laytpl, table = layui.table, util = layui.util;

					// 监听提交
					form.on('submit(search-jwc)', function(data) {
						table.reload('jwc-list-table', {
							where : data.field
						});
					});
					// 监听提交
					form.on('submit(search)', function(data) {
						table.reload('list-table', {
							where : data.field
						});
					});
				})
			</script>
			<script type="text/html" id="urlTpl">
				<div><a target="_blank" href="{{ d.url }}"> 查看或下载</a></div>
			</script>
			<script type="text/html" id="createtimeTpl">
				<div>{{layui.util.toDateString(d.createtime, 'yyyy-MM-dd HH:mm:ss')}}</div>
			</script>
			<script type="text/html" id="crawltimeTpl">
				<div>{{ layui.util.toDateString(d.crawltime, 'yyyy-MM-dd') }}</div>
			</script>
</body>
</html>