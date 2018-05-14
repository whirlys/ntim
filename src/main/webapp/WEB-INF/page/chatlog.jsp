<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>聊天记录</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script src="${ctx}/layui/layui.js"></script>
<style>
body .layim-chat-main {
	height: auto;
}
</style>

</head>
<body>


	<div class="layim-chat-main">
		<ul id="LAY_view">

		</ul>
	</div>

	<div id="LAY_page" style="margin: 0 10px;"></div>


	<div title="消息模版" id="LAY_tpl" style="display: none;">

		<li class="layim-chat-mine"><div class="layim-chat-user">
				<img
					src="http://10.100.99.220:9999/group1/M00/00/00/CmRj3Fra9w-AAk8SAAAVD3wcBXs491.png"><cite><i>2018-09-09</i>小旋锋</cite>
			</div>
			<div class="layim-chat-text">这是一个聊天内容</div></li>

		<li><div class="layim-chat-user">
				<img
					src="http://10.100.99.220:9999/group1/M00/00/00/CmRj3Fra9w-AAk8SAAAVD3wcBXs491.png"><cite>对方名称<i>2018-55-55</i></cite>
			</div>
			<div class="layim-chat-text">这是回复</div></li>


	</div>

	<script>
		layui.use([ 'layim', 'laypage' ], function() {
			var layim = layui.layim, layer = layui.layer, laytpl = layui.laytpl, $ = layui.jquery, laypage = layui.laypage, param = location.search;

		});
	</script>
</body>
</html>
