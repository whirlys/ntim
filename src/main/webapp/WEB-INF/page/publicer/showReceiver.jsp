<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知阅读情况</title>
<style type="text/css">
.username {
	line-height: 30px;
	font-size: 16px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">已读学生 <span class="layui-badge">${readList.count }</span></li>
			<li>未读学生 <span class="layui-badge layui-bg-green">${notReadList.count }</span></li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<ul class="layui-timeline">
					<c:forEach items="${readList.groups }" var="group">
						<li class="layui-timeline-item"><i
							class="layui-icon layui-timeline-axis">&#xe63f;</i>
							<div class="layui-timeline-content layui-text">
								<h3 class="layui-timeline-title">${group.groupname }
									<span class="layui-badge">${group.count } 人已读</span>
								</h3>
								<p class="layui-row username ">
									<c:forEach items="${group.list }" var="user">
										<span class="layui-col-xs4">${user.username }</span>
									</c:forEach>
								</p>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<div class="layui-tab-item">
				<ul class="layui-timeline">
					<c:forEach items="${notReadList.groups }" var="group">
						<li class="layui-timeline-item"><i
							class="layui-icon layui-timeline-axis">&#xe63f;</i>
							<div class="layui-timeline-content layui-text">
								<h3 class="layui-timeline-title">${group.groupname }
									<span class="layui-badge layui-bg-green">${group.count }
										人未读</span>
								</h3>
								<p class="layui-row username ">
									<c:forEach items="${group.list }" var="user">
										<span class="layui-col-xs4">${user.username }</span>
									</c:forEach>
								</p>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
</body>
</html>