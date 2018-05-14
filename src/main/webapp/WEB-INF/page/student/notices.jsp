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
<title>我的通知</title>
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
				<label class="layui-form-label">发布人</label>
				<div class="layui-input-block">
					<select name="senderId">
						<option value=""></option>
						<c:forEach var="publicer" items="${publicerVos }">
							<option value="${publicer.id }">${publicer.username }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class=" layui-inline">
				<label class="layui-form-label">重要等级</label>
				<div class="layui-input-block">
					<select name="lever">
						<option value=""></option>
						<c:forEach var="lever" items="<%=NoticeLever.values()%>">
							<option value="${lever.lever }">${lever.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class=" layui-inline">
				<label class="layui-form-label">类型</label>
				<div class="layui-input-block">
					<select name="type">
						<option value=""></option>
						<c:forEach var="item" items="<%=NoticeType.values()%>">
							<option value="${item.name }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="q" class="layui-input searchVal"
						placeholder="根据关键字查找标题或描述" />
				</div>
				<a class="layui-btn " lay-submit lay-filter="search"
					data-type="reload">搜索</a>
			</div>
		</form>
	</blockquote>

	<table class="layui-table"
		lay-data="{height:515, url:'${ctx }/student/notices', page:true, id:'list-table'}"
		lay-filter="list">
		<thead>
			<tr>
				<th lay-data="{field:'noticeId', width:80, sort: true}">ID</th>
				<th lay-data="{field:'publicer', width:200}">发布人</th>
				<th lay-data="{field:'title', width:300,templet:'#titleTpl'}">标题</th>

				<th lay-data="{field:'leverName',width:150 }">重要等级</th>
				<th lay-data="{field:'type', sort: true,width:150}">类型</th>
				<th
					lay-data="{field:'createtime',width:180 ,templet:'#createTimeTpl'}">发布时间</th>
				<th
					lay-data="{width:150, align:'center', toolbar: '#listBar', width:150}">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/html" id="titleTpl">
		{{#  if(d.isRead == true){ }}
    		{{d.title}}
  		{{#  } else { }}
    		<span class="layui-badge layui-bg-blue">未读</span> {{d.title}}
  		{{#  } }}
	</script>
	<script type="text/html" id="createTimeTpl">
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

			setInterval(function() {
				table.reload('list-table', {});
			}, 10 * 1000); // 定时刷新
			// 监听工具条 编辑
			table.on('tool(list)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				var tr = obj.tr;
				var id = data.timelineId;
				if (layEvent === 'detail') { //删除
					var url = "${ ctx}/student/notice/" + id;
					var title = "通知详情";
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