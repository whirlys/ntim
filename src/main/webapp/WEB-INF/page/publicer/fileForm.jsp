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
			<label class="layui-form-label">文件URL</label>


			<div class="layui-input-inline">
				<input type="text" name="url" placeholder="手动输入文件URL"
					autocomplete="off" class="layui-input" lay-verify="required">
			</div>
			或
			<button type="button" class="layui-btn" id="test1">
				<i class="layui-icon">&#xe67c;</i>上传文件
			</button>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文件名称</label>
			<div class="layui-input-block">
				<input type="text" name="filename" placeholder="请输入文件名称"
					lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">文件描述</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea"></textarea>
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
		layui.use([ 'form', 'upload', 'layer', 'jquery' ], function() {
			var form = layui.form, layer = layui.layer, $ = layui.jquery;

			var upload = layui.upload;

			//执行实例
			var uploadInst = upload.render({
				elem : '#test1' //绑定元素
				,
				url : '${ctx}/upload/file' //上传接口
				,
				done : function(res) {
					//上传完毕回调
					if (res.code == 0) {
						$("input[name='filename']").val(res.data.title);
						$("input[name='url']").val(res.data.src);
					} else {
						layer.alert(JSON.stringify(res))
					}
				},
				error : function(res) {
					//请求异常回调
					alyer.alert(JSON.stringify(res))
				}
			});
			//监听提交
			form.on('submit(formDemo)', function(data) {
				$.ajax({
					url : "${ctx }/publicer/addFile",
					data : JSON.stringify(data.field),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == 100) {
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