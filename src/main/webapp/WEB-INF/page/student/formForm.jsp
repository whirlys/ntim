<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.whirly.enums.NoticeLever,com.whirly.enums.NoticeType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写表单</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<style type="text/css">
.form-message {
	padding-top: 70px;
	padding-left: 70px;
	padding-right: 70px;
	padding-bottom: 20px;
}

.form-message h2 {
	text-align: center;
}

.form-message p {
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="layui-container">
		<form class="layui-form">
			<div class="form-message">
				<h2>${form.formName }</h2>
				<div>${form.description }</div>
				<p style="color: red;">请在 ${form.formatDeadline } 之前完成该表单！</p>
			</div>
			<c:forEach items="${form.fields}" var="field">
				<div class="layui-form-item">
					<label class="layui-form-label">${field.name }<c:if
							test='${field.required == true}'>
							<span style='color: red;'> *</span>
						</c:if></label>
					<div class="layui-input-block">
						<input type="text" name="${field.name }"
							placeholder="请输入${field.name }"
							<c:if test='${field.required == true}'> lay-verify="required" </c:if>
							autocomplete="off" class="layui-input">
					</div>
				</div>
			</c:forEach>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
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
				var value = JSON.stringify(data.field);
				value['value'] = data.field;
				$.ajax({
					url : "${ctx }/student/form/${form.formId}",
					data : value,
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == 100) {
							layer.alert("填写完成，感谢您的参与！", {
								end : function() {
									window.location.href = "about:blank";
									window.close();
								}
							});

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