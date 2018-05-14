<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知详情</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<style>
.layui-row {
	margin-bottom: 50px;
}

.title {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

.noticeInfo {
	text-align: center;
}

.noticeInfo>span {
	margin: 10px 10px;
}

.content {
	margin: 20px;
	padding: 15px;
	border: solid 1px #bbb;
	box-shadow: 5px 5px 2px 2px #888888;
	font-size: 16px;
	line-height: 25px;
}

.notread {
	float: right;
}

.supplement {
	clear: both;
	margin-top: 30px;
}

.supplement .layui-btn {
	float: right;
}
</style>
</head>
<body>

	<div style="padding: 20px;">
		<div class="layui-row">
			<div class="layui-col-md6">
				<h2 class="title">${notice.title }</h2>
				<div class="noticeInfo">
					<span>发布者：${notice.publicer }</span><span>发布时间：${notice.formatCreateTime }
					</span><span>类型：${notice.type } </span><span>级别：
						${notice.leverName} </span>
				</div>

				<div class="content">${notice.content }</div>

				<div class="notread">
					接收人 ；共 <span>${readNum + notReadNum }</span> 人 <span>${notReadNum }</span>
					人未读 <a id="showReceiver" href="javascript:;">查看》</a>
				</div>
				<hr>
				<div class="supplement">
					<h2>
						补充说明
						<button
							class="layui-btn layui-btn-normal  layui-btn-xs addSupplement">添加说明</button>
					</h2>
					<div class="layui-collapse">
						<c:forEach items="${supplements }" var="s" varStatus="vs">
							<div class="layui-colla-item">
								<h2 class="layui-colla-title">
									补充说明${vs.count } <span style="float: right;"> <fmt:formatDate
											value="${s.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</h2>
								<div class="layui-colla-content layui-show">${s.content }
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<h2>讨论消息</h2>
				<div></div>
			</div>
		</div>
	</div>
	<script src="${ctx}/layui/layui.js"></script>
	<script>
		//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'layer', 'jquery' ], function() {
			var element = layui.element, layer = layui.layer, $ = layui.jquery;

			$('#showReceiver').click(function() {
				layer.open({
					title : "通知阅读情况",
					area : [ '800px', '500px' ],
					shadeClose : true,
					type : 2,
					content : '${ctx}/publicer/showReceiver/${ notice.noticeId}'
				});
			});

			$('.addSupplement').click(function() {
				layer.open({
					title : "添加补充说明",
					area : [ '800px', '500px' ],
					shadeClose : true,
					type : 2,
					content : '${ctx}/publicer/addSupplement/${ notice.noticeId}',
					end : function(layero, index) {
						window.location.reload();
					}
				});
			});

		});
	</script>
</body>
</html>