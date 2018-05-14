<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生表单</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<br>
	<form class="layui-form">
		<input hidden="hidden" name="studentId" value="${student.studentId}">
		<div class="layui-form-item">
			<label class="layui-form-label">学号</label>
			<div class="layui-input-block">
				<input type="text" name="account" required lay-verify="required"
					placeholder="请输入学号" autocomplete="off" class="layui-input"
					value="${student.user.account }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" name="username" required lay-verify="required"
					placeholder="请输入姓名" autocomplete="off" class="layui-input"
					value="${student.user.username }">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">院系</label>
			<div class="layui-input-block">
				<select lay-filter="departmentChange">
					<option value=""></option>
					<c:forEach var="d" items="${departments }">
						<c:if test="${d.departmentId ==  dpId}">
							<option value="${d.departmentId }" selected="selected">${d.name }</option>
						</c:if>
						<c:if test="${d.departmentId !=  dpId}">
							<option value="${d.departmentId }">${d.name }</option>
						</c:if>

					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">班级</label>
			<div class="layui-input-block">
				<select name="classId" lay-verify="required" id="classid">
					<option value=" ${student.classs.classId }">${student.classs.grade }${student.classs.profession }${student.classs.classNumber }班</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<c:if test="${student.sex == true}">
					<input type="radio" name="sex" value="1" title="男" checked>
					<input type="radio" name="sex" value="0" title="女">
				</c:if>
				<c:if test="${student.sex == false}">
					<input type="radio" name="sex" value="1" title="男">
					<input type="radio" name="sex" value="0" title="女" checked>
				</c:if>

			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">生日</label>
			<div class="layui-input-block">
				<input type="text" name="birthday" placeholder="请输入生日"
					autocomplete="off" class="layui-input"
					value='<fmt:formatDate value="${student.birthday }" pattern="yyyy-MM-dd"/>'
					id="birthday">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生类型</label>
			<div class="layui-input-block">
				<select name="studentType" lay-verify="required">
					<option value="本科生">本科生</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">长号</label>
			<div class="layui-input-block">
				<input type="number" name="longPhone" 
					placeholder="请输入长号" autocomplete="off" class="layui-input"
					value="${student.longPhone }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">短号</label>
			<div class="layui-input-block">
				<input type="number" name="shortPhone" placeholder="请输入短号" autocomplete="off"
					class="layui-input" value="${student.shortPhone }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">班中职务</label>
			<div class="layui-input-block">
				<select name="duty" lay-verify="required">
					<c:forTokens items="群众,班长,学习委员,副班长,团支书,生活委员,体育委员" delims=","
						var="title">
						<c:if test="${student.duty == title }">
							<option value="${title }" selected="selected">${title }</option>
						</c:if>
						<c:if test="${student.duty != title }">
							<option value="${title }">${title }</option>
						</c:if>

					</c:forTokens>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">家长号码</label>
			<div class="layui-input-block">
				<input type="number" name="familyPhone"  placeholder="请输入家长号码" autocomplete="off"
					class="layui-input" value="${student.familyPhone }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="email" name="email" 
					placeholder="请输入邮箱" autocomplete="off" class="layui-input"
					value="${student.email }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">QQ号码</label>
			<div class="layui-input-block">
				<input type="number" name="qq" 
					placeholder="请输入qq" autocomplete="off" class="layui-input"
					value="${student.qq }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证号码</label>
			<div class="layui-input-block">
				<input type="text" name="idNumber" 
					placeholder="请输入身份证号码" autocomplete="off" class="layui-input"
					value="${student.idNumber }">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">家庭住址</label>
			<div class="layui-input-block">
				<textarea name="homeAddress" placeholder="请输入家庭住址"
					class="layui-textarea">${student.homeAddress }</textarea>
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
		layui.use([ 'form', 'laydate' ], function() {
			var form = layui.form, $ = layui.jquery, laydate = layui.laydate;

			laydate.render({
				elem : '#birthday' //指定元素
			});

			form.on('select(departmentChange)', function(data) {
				if (data.value == null | data.value == '') {
					$("#classid").val('<option value=""></option>');

				} else {
					$.ajax({
						type : 'POST',
						url : '${ctx}/admin/selectClasssByDep',
						data : {
							departmentId : data.value
						},
						dataType : 'json',
						success : function(data) {
							$("#classid").html("");
							$.each(data['classslist'], function(key, val) {
								var option1 = $("<option>").val(val.classId).text(val.grade + val.profession + val.classNumber + "班");
								$("#classid").append(option1);
								form.render('select');
							});
							$("#classid").get(0).selectedIndex = 0;
						}
					});
				}
			});
			//监听提交
			form.on('submit(formDemo)', function(data) {
				$.ajax({
					url : "${ctx}/admin/studentpost",
					data : data.field,
					method : "post",
					dataType : 'json',
					success : function(data) {

						if (data.code == 100) {
							layer.msg(data.msg);
							layer.closeAll("iframe");
							//刷新父页面
							parent.location.reload();
						} else if (data.code == 300) {
							layer.msg(data.map.error);
						} else {
							layer.msg(data.msg);
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