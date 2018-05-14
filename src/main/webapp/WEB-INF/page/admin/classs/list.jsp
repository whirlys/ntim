<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>班级列表</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />

<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/font-awesome.min.css">
<link href="${ctx}/css/list.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/animate.min.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/list.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<link href="${ctx }/css/pace-theme-flash.css" id="pacecss"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" data-pace-option='{"ajax":false}'
	src="${ctx }/js/pace.min.js"></script>


<style>
.layui-icon {
	margin-top: 10px !important;
	margin-right: 2px !important;
}

.layui-table {
	color: black !important;
	font: 14px Helvetica Neue, Helvetica, PingFang SC, 微软雅黑, Tahoma, Arial,
		sans-serif;
}

.layui-table-cell {
	height: 35px;
	line-height: 35px;
	padding: 0 20%;
}

.layui-badge {
	height: 18px;
	line-height: 18px;
	display: inline-block;
	margin-top: 7px;
}

.layui-form-switch {
	margin-top: 6px !important;
}
</style>
</head>
<body>

	<form class="layui-form">
		<blockquote class="layui-elem-quote news_search">

			<div class="layui-inline">
				<label class="layui-form-label">按院系查询</label>
				<div class="layui-input-block">
					<select name="interest" lay-filter="type" id="type1">
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input value="" placeholder="请输入关键字" id="condition"
						class="layui-input search_input" type="text">
				</div>
				<a class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn addblog" style="background-color: #5FB878">
					<i class="layui-icon">&#xe608;</i>添加班级
				</a>
			</div>

		</blockquote>
	</form>
	<table id="blogTable" class="layui-table" lay-filter="blogtab"
		lay-data="{id: 'blogTable'}"></table>
</body>

<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-xs layui-bg-orange" lay-event="showStudent">查看学生</a>
</script>
<script>
	layui.use([ 'layer', 'table', 'form' ], function() {
		var table = layui.table;
		var layer = layui.layer;
		var form = layui.form;
		var $ = layui.jquery;
		//加载分类
		$.ajax({
			type : "get",
			url : "${ctx}/admin/departmentlistajax",
			dataType : "json",
			success : function(res) {
				$("#type1").append('<option ></option>');
				if (res.length > 0) {
					$.each(res, function(k, v) {
						$("#type1").append('<option value=' + v.departmentId + '>' + v.name + '</option>');
					});
				}
				form.render('select');
			}
		});
		var option = {
			elem : '#blogTable',
			url : '${ctx}/admin/classslist',
			request : {
				pageName : 'page', //页码的参数名称，默认：page
				limitName : 'limit' //每页数据量的参数名，默认：limit
			},
			limit : 10,
			limits : [ 5, 10, 15, 30 ],
			height : 500,
			cols : [ [

			{
				type : 'checkbox',
				width : 50,
			}, {
				field : 'classId',
				title : 'ID',
				sort : true,
				width : 80
			}, {
				field : 'departmentName',
				title : '学院',
				sort : true,
				width : 250
			}

			, {
				field : 'grade',
				title : '年级',
				sort : true,
				width : 150
			}, {
				field : 'profession',
				title : '专业',
				width : 250
			}, {
				field : 'classNumber',
				title : '班级',
				width : 80
			}, {
				title : '操作',
				align : 'center',
				toolbar : '#barDemo',
				width : 250
			} ] ],
			page : true
		};
		var tableins = table.render(option);

		//绑定事件
		$(".addblog").on("click", function() {

			var index = layui.layer.open({
				title : "添加班级",
				type : 2,
				maxmin : true,
				area : [ "100%", "100%" ],
				content : "classform",
				success : function(layero, index) {
					setTimeout(function() {
						layui.layer.tips('这里是关闭窗口', '.layui-layer-setwin .layui-layer-close', {
							tips : 3
						});
					}, 500)
				}
			});

		});

		//查询按钮
		$(".search_btn").click(function() {

			tableins.reload({
				where : { //设定异步数据接口的额外参数，任意设
					departmentId : $("#type1").val(),
					q : $("#condition").val()
				},
				page : {
					curr : 1
				//重新从第 1 页开始
				}
			});

		});

		//监听工具条
		table.on('tool(blogtab)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				var index = layui.layer.open({
					title : "修改班级",
					type : 2,
					maxmin : true,
					content : "classform?classsid=" + data.classId,
					success : function(layero, index) {
						setTimeout(function() {
							layui.layer.tips('这里是关闭窗口', '.layui-layer-setwin .layui-layer-close', {
								tips : 3
							});
						}, 500)
					}
				});
				layui.layer.full(index);
			} else if (obj.event === 'showStudent') {
				var index = layui.layer.open({
					title : "查看班级成员",
					type : 2,
					maxmin : true,
					content : "showStudent?classsid=" + data.classId,
					success : function(layero, index) {

					}
				});
				layui.layer.full(index);
			}
		});

	});
</script>

</html>