<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>学生列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${ctx}/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<blockquote class="layui-elem-quote quoteBox">

			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" id="search_q" class="layui-input searchVal" style="width: 250px;"
						placeholder="根据姓名、学号或邮箱查找..." />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addstu_btn">添加学生</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-warning batch-import">导入学生</a>
			</div>

		</blockquote>
	</form>
	<table id="stuList" lay-filter="stuList"></table>

	<!--操作-->
	<script type="text/html" id="stuListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	</script>

	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'form', 'layer', 'laydate', 'table', 'laytpl' ], function() {
			var form = layui.form, layer = parent.layer === undefined ? layui.layer : top.layer, $ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table;

			var tableins = table.render({
				elem : '#stuList',
				url : '${ctx}/admin/studentlist',
				request : {
					pageName : 'page', //页码的参数名称，默认：page
					limitName : 'limit' //每页数据量的参数名，默认：limit
				},
				limits : [ 5, 10, 15, 30, 50, 100, 150 ],
				cols : [ [

				{
					type : 'checkbox',
					width : 50,
				}, {
					field : 'account',
					title : '学号',
					sort : true,
					width : 130
				}, {
					field : 'username',
					title : '姓名',
					sort : true,
					width : 130
				}, {
					field : 'departmentName',
					title : '学院',
					sort : true,
					width : 150
				}

				, {
					field : 'grade',
					title : '年级',
					sort : true,
					width : 80
				}, {
					field : 'profession',
					title : '专业',
					width : 120
				}, {
					field : 'classNumber',
					title : '班级',
					width : 60
				}, {
					field : 'duty',
					title : '班级职位',
					width : 100
				},

				{
					field : 'studentType',
					title : '类型',
					width : 80
				}, {
					field : 'longPhone',
					title : '手机号码',
					width : 120
				}, {
					field : 'sex',
					title : '性别',
					width : 80,
					templet : function(d) {
						if (d.sex == false) {
							return '女';
						} else if (d.sex == true) {
							return '男';
						} else {
							return '';
						}

					}
				},

				{
					title : '操作',
					align : 'center',
					toolbar : '#stuListBar',
					width : 100
				} ] ],
				page : true
			});

			// 添加学生
			function addStudent(edit) {
				var index = layui.layer.open({
					title : "添加学生",
					type : 2,
					content : "${ctx}/admin/stuform",
					success : function(layero, index) {

					}
				})
				layui.layer.full(index);
				//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				})
			}

			// 添加学生 点击事件
			$(".addstu_btn").click(function() {
				addStudent();
			})

			//查询按钮
			$(".search_btn").click(function() {
				tableins.reload({
					where : { //设定异步数据接口的额外参数，任意设
						q : $("#search_q").val()
					},
					page : {
						curr : 1
					//重新从第 1 页开始
					}
				});

			});

			// 批量导入学生
			$(".batch-import").click(function() {
				var index = layui.layer.open({
					title : "批量导入学生",
					type : 2,
					content : "${ctx}/admin/tobatchimport",
					success : function(layero, index) {

					}
				})
				layui.layer.full(index);
				//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				})
			});

			//监听工具条
			table.on('tool(stuList)', function(obj) {
				var data = obj.data;
				if (obj.event === 'edit') {
					var index = layui.layer.open({
						title : "修改学生信息",
						type : 2,
						maxmin : true,
						content : "stuform?studentId=" + data.studentId,
						success : function(layero, index) {
							setTimeout(function() {
								layui.layer.tips('这里是关闭窗口', '.layui-layer-setwin .layui-layer-close', {
									tips : 3
								});
							}, 500)
						}
					});
					layui.layer.full(index);
				}
			});
		})
	</script>
</body>
</html>