<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加文件</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">问题标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" placeholder="输入问题标题"
					autocomplete="off" class="layui-input" lay-verify="required">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">问题描述</label>
			<div class="layui-input-block">
				<textarea id="content" style="display: none;" name="content"></textarea>
			</div>
		</div>

		<div class="layui-form-item" pane="">
			<label class="layui-form-label">是否匿名</label>
			<div class="layui-input-block">
				<input type="checkbox" name="isActive" lay-skin="switch"
					title="是否匿名" lay-text="是|否">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
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
				height : 500
			}); //建立编辑器
			//监听提交
			form.on('submit(formDemo)', function(data) {
				if (layedit.getText(contentIndex).length < 6) {
					layer.alert("问题描述的内容长度不能小于6");
					return false;
				}

				var fields = {};
				fields['title'] = data.field.title;
				fields['description'] = layedit.getContent(contentIndex);
				if ($('input[type="checkbox"]').prop("checked") == true) {
					fields["anonymous"] = true;
				} else {
					fields["anonymous"] = false;
				}

				$.ajax({
					url : "${ctx }/question/addQuestion",
					data : JSON.stringify(fields),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == "100") {
							layer.closeAll("iframe");
							//刷新父页面
							parent.location.reload();
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