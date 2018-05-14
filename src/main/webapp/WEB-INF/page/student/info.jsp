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
<style>
.
fly-panel-user {
	padding: 20px;
}
</style>
</head>
<body>

	<div class="fly-panel fly-panel-user" pad20="">
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">我的资料</li>
				<li lay-id="avatar" class="">头像</li>
				<li lay-id="pass" class="">密码</li>

			</ul>
			<div class="layui-tab-content" style="padding: 20px 0;">
				<div class="layui-form layui-form-pane layui-tab-item layui-show">
					<form method="post">
						<div class="layui-container">
							<div class="layui-row">
								<div class="layui-col-md6">
									<div class="layui-form-item">
										<label class="layui-form-label">学号</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.account }" class="layui-input" disabled
												style="cursor: not-allowed !important;">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">姓名</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.username }" class="layui-input" disabled
												style="cursor: not-allowed !important;">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">院系</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.departmentName }" class="layui-input"
												disabled style="cursor: not-allowed !important;">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">专业</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.profession }" class="layui-input"
												disabled style="cursor: not-allowed !important;">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">年级</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.grade }" class="layui-input" disabled
												style="cursor: not-allowed !important;">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">班级</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.classNumber }" class="layui-input"
												disabled style="cursor: not-allowed !important;">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">类型</label>
										<div class="layui-input-inline">
											<input type="text" autocomplete="off"
												value="${studentVo.studentType }" class="layui-input"
												disabled style="cursor: not-allowed !important;">
										</div>
									</div>
								</div>
								<div class="layui-col-md6">

									<div class="layui-form-item">
										<label class="layui-form-label">性别</label>
										<div class="layui-input-block">
											<c:choose>
												<c:when test="${studentVo.sex == true }">
													<input type="radio" name="sex" value="true" title="男"
														checked>
													<input type="radio" name="sex" value="false" title="女">
												</c:when>
												<c:otherwise>
													<input type="radio" name="sex" value="true" title="男">
													<input type="radio" name="sex" value="false" title="女"
														checked>
												</c:otherwise>
											</c:choose>

										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">长号</label>
										<div class="layui-input-inline">
											<input type="text" name="longPhone" autocomplete="off"
												value="${studentVo.longPhone }" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">短号</label>
										<div class="layui-input-inline">
											<input type="text" name="shortPhone" autocomplete="off"
												value="${studentVo.shortPhone }" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">班中职务</label>
										<div class="layui-input-inline">
											<input type="text" name="duty" autocomplete="off"
												value="${studentVo.duty }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">email</label>
										<div class="layui-input-inline">
											<input type="text" name="email" autocomplete="off"
												value="${studentVo.email }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">身份证号</label>
										<div class="layui-input-inline">
											<input type="text" name="idNumber" autocomplete="off"
												value="${studentVo.idNumber }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">家庭住址</label>
										<div class="layui-input-inline">
											<input type="text" name="homeAddress" autocomplete="off"
												value="${studentVo.homeAddress }" class="layui-input">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-container">
							<div class="layui-form-item">
								<button class="layui-btn" key="set-mine" lay-filter="info"
									lay-submit="">确认修改</button>
							</div>
						</div>
					</form>
				</div>
				<div class="layui-form layui-form-pane layui-tab-item">
					<div class="layui-container">
						<div class="layui-form-item">


							<button type="button" class="layui-btn upload-img"
								id="avatar-btn">
								<i class="layui-icon"></i>上传头像
							</button>
							<div class="layui-input-block">
								<img class="avatar" src="${studentVo.avatar }"> <span
									class="loading"></span>
							</div>

						</div>
					</div>
				</div>
				<div class="layui-form layui-form-pane layui-tab-item">
					<div class="layui-container">
						<div class="layui-form-item">
							<label for="curr_pass" class="layui-form-label">当前密码</label>
							<div class="layui-input-inline">
								<input type="password" id="curr_pass" name="password"
									lay-verify="required" autocomplete="off" class="layui-input">
							</div>

						</div>
						<div class="layui-form-item">
							<label for="L_pass" class="layui-form-label">新密码</label>
							<div class="layui-input-inline">
								<input type="password" id="L_pass" name="newpassword"
									lay-verify="required" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">6到16个字符</div>
						</div>
						<div class="layui-form-item">
							<label for="L_repass" class="layui-form-label">确认密码</label>
							<div class="layui-input-inline">
								<input type="password" id="L_repass" name="repassword"
									lay-verify="required" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<button class="layui-btn" key="set-mine" lay-filter="changePass"
								lay-submit="">确认修改</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'element', 'form', 'jquery', 'upload', 'layer' ], function() {
			var element = layui.element, form = layui.form, $ = layui.jquery, upload = layui.upload, layer = layui.layer;

			//监听提交
			form.on('submit(info)', function(data) {
				$.ajax({
					url : "${ctx }/student/editInfo",
					data : JSON.stringify(data.field),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == 100) {
							layer.alert("修改成功")
						} else {
							layer.alert(JSON.stringify(data))
						}
					},
					error : function(xhr, textStatus) {

					},
				});
				return false;
			});

			//执行实例
			var uploadInst = upload.render({
				elem : '#avatar-btn' //绑定元素
				,
				url : '${ctx}/upload/avatar' //上传接口
				,
				done : function(res) {
					//上传完毕回调

					$("img.avatar").attr("src", res.data.src);

				},
				error : function() {
					//请求异常回调
				}
			});

			//监听提交
			form.on('submit(changePass)', function(data) {
				if (data.field.newpassword.length < 6) {
					layer.alert("密码长度须 6到16个字符")
					return false;
				}
				if (data.field.newpassword != data.field.repassword) {
					layer.alert("两次密码不一致");
					return false;
				}
				$.ajax({
					url : "${ctx }/student/changePassword",
					data : JSON.stringify(data.field),
					method : "post",
					dataType : 'json',
					contentType : 'application/json',
					traditional : true,
					success : function(data) {
						if (data['code'] == 100) {
							layer.alert("修改成功")
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