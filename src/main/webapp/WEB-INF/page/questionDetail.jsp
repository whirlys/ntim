<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="${ctx }/css/amazeui.min.css">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

<style>
.detail-box {
	padding: 20px;
}

.fly-detail-info {
	position: relative;
	margin: 10px 0 15px;
}

.fly-detail-info>span {
	margin: 10px;
	line-height: 20px;
	text-align: center;
}

.fly-detail-column {
	line-height: 20px;
}

.detail-about {
	position: relative;
	line-height: 20px;
	padding: 15px 15px 15px 75px;
	font-size: 13px;
	background-color: #f8f8f8;
	color: #999;
}

.detail-body {
	margin: 20px 0 0;
	min-height: 100px;
	line-height: 26px;
	font-size: 16px;
	color: #333;
	word-wrap: break-word;
}

.comment-pane {
	margin-bottom: 20px;
}

img.upload-img {
	width: 100%;
}
</style>
</head>

<body>
	<div class="layui-row">
		<div class="layui-col-md9">
			<div class="fly-panel detail-box">
				<h1>${question.title }</h1>
				<div class="fly-detail-info">
					<span class="layui-badge layui-bg-green fly-detail-column">${question.departmentName }</span>
					<span class="layui-badge layui-bg-red fly-detail-column">${question.username }</span>
					<span class="layui-badge layui-bg-blue fly-detail-column">${question.formatCreateTime }</span>
				</div>
				<div class="detail-body">${question.description }</div>
				<div class="fly-panel detail-box">
					<fieldset class="layui-elem-field layui-field-title"
						style="text-align: center;">
						<legend>回帖</legend>
					</fieldset>
					<div class="comment-pane">
						<ul class="am-comments-list am-comments-list-flip">
							<c:forEach items="${answerVOs }" var="answer">
								<li class="am-comment"><a href="#link-to-user-home"> <img
										src="${answer.avatar }" alt="" class="am-comment-avatar"
										width="48" height="48" />
								</a>
									<div class="am-comment-main">
										<header class="am-comment-hd">

											<div class="am-comment-meta">
												<a href="javascript:;" class="am-comment-author">${answer.username }</a>
												评论于
												<time datetime="2013-07-27T04:54:29-07:00"
													title="2013年7月27日 下午7:54 格林尼治标准时间+0800">
													<fmt:formatDate value="${answer.createtime}"
														pattern="yyyy-MM-dd HH:mm:ss" />
												</time>
											</div>
										</header>
										<div class="am-comment-bd">${answer.answer }</div>
									</div></li>
							</c:forEach>
						</ul>
					</div>
					<div class="layui-form layui-form-pane">
						<form method="post">
							<div class="layui-form-item" pane="">
								<label class="layui-form-label">是否匿名</label>
								<div class="layui-input-block">
									<input type="checkbox" name="isActive" lay-skin="switch"
										title="是否匿名" lay-text="是|否">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">评论</label>
								<div class="layui-input-block">
									<textarea id="content" style="display: none;"></textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<input type="hidden" name="jid" value="25376"> <input
									type="hidden" name="daPages" value="0">
								<button class="layui-btn" lay-filter="*" lay-submit="">提交回复</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-col-md3"></div>
	</div>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		//Demo
		layui.use([ 'form', 'upload', 'layer', 'jquery', 'layedit' ], function() {
			var form = layui.form, layer = layui.layer, $ = layui.jquery;

			var upload = layui.upload;

			var layedit = layui.layedit;
			layedit.set({
				uploadImage : {
					url : '${ctx}/upload/layedit' //接口url
				}
			});
			var contentIndex = layedit.build("content", {

			}); //建立编辑器
			//监听提交
			form.on('submit(*)', function(data) {
				if (layedit.getText(contentIndex).length < 1) {
					layer.alert("问题描述的内容不能为空");
					return false;
				}

				var fields = {};

				fields['answer'] = layedit.getContent(contentIndex);
				if ($('input[type="checkbox"]').prop("checked") == true) {
					fields["anonymous"] = true;
				} else {
					fields["anonymous"] = false;
				}

				$.ajax({
					url : "${ctx }/question/addAnswer/${question.questionId}",
					data : JSON.stringify(fields),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == 100) {
							//layer.closeAll("iframe");
							//刷新父页面
							location.reload();
						} else {
							layer.alert(JSON.stringify(data))
						}
					},
					error : function(xhr, textStatus) {

					},
				});
				return false;
			});
		});
	</script>
</body>
</html>