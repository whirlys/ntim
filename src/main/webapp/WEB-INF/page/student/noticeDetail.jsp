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
<link rel="stylesheet" href="${ctx }/css/amazeui.min.css">
<script type="text/javascript"
	src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
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
					人未读
				</div>
				<hr>
				<div class="supplement">
					<h2>补充说明</h2>
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
				<div class="chat-content">
					<ul class="am-comments-list am-comments-list-flip" id="chat-list">

					</ul>
				</div>
				<form class="am-form-inline " role="form" id="send-form"
					data-userid="${sessionScope.user.userId }"
					data-id="${notice.noticeId }"
					action="${ctx }/publicer/addNoticeMessage">
					<div class="am-form-group">
						<input type="text" class="am-form-field " placeholder="输入消息"
							id="send-content"> <a type="submit"
							class="am-btn am-btn-primary am-icon-send-o" href="javascript:;"
							id="send-chat"> 发送</a>
					</div>


				</form>
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
					area : [ '800px', '600px' ],
					shadeClose : true,
					type : 2,
					content : '${ctx}/publicer/showReceiver/${ notice.noticeId}'
				});
			});

			$('.addSupplement').click(function() {
				layer.open({
					title : "添加补充说明",
					area : [ '800px', '600px' ],
					shadeClose : true,
					type : 2,
					content : '${ctx}/publicer/addSupplement/${ notice.noticeId}',
					end : function(layero, index) {
						window.location.reload();
					}
				});
			});

			$("#send-chat").click(function() {
				var data = {};
				data["content"] = $("#send-content").val();
				data["noticeId"] = $("#send-form").data("id");
				var url = $("#send-form").attr("action");
				if (data.content.length == 0) {
					return false;
				}

				console.log("noticeId=" + data.noticeId + " , content=" + data.content + " , action=" + url)
				$.ajax({
					url : url,
					data : JSON.stringify(data),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						$("#send-content").val("");
					},
					error : function(xhr, textStatus) {

					},
				});
			})
			maxId = 0; // 当前最大 messageId
			function fetchNoticeMessage() {
				var url = "${ctx}/publicer/noticeMessage?noticeId=${notice.noticeId}&baseId=" + maxId;
				var userId = $("#send-form").data("userid");

				$.get(url, function(data) {
					var len = data.map.messages.length;
					if (len == 0) {
						return false;
					}
					maxId = data.map.messages[len - 1].messageId;
					var html = '';
					$.each(data.map.messages, function(index, item) {

						html += '<li class="am-comment';

						if (item.userId == userId) {
							console.log("item.userId=" + item.userId + " userId=" + userId)
							html += ' am-comment-flip'
						}

						html += ' am-comment-danger"><a	href="#link-to-user-home"><img src="'
								+ item.avatar
								+ '"class="am-comment-avatar" width="48" height="48"></a><div class="am-comment-main"><header class="am-comment-hd"><div class="am-comment-meta"><a href="#link-to-user" class="am-comment-author">' + item.username + '</a> 发表于<time datetime="'
								+ item.createtime
								+ '" ">' + item.createtime + '</time></div></header><div class="am-comment-bd"><p>' + item.content + '</p></div></div></li>';

					});
					$("#chat-list").append(html);
				});
			}

			fetchNoticeMessage();

			setInterval(function() {
				fetchNoticeMessage();
			}, 3 * 1000);

		});
	</script>
</body>
</html>