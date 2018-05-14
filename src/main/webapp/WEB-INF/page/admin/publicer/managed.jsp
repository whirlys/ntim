<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布者班级管理</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body style="padding: 20px">
	<div class="layui-btn-group demoTable">
		<button class="layui-btn layui-btn-danger" data-type="delManaged">删除管理班级</button>
	</div>

	<table class="layui-table"
		lay-data="{url:'${ctx }/admin/publicer/inManagedVo?publicerId=${publicer.publicerId }', page:false, id:'clist'}">
		<thead>
			<tr>

				<th lay-data="{checkbox : true, fixed: 'left'}"></th>
				<th lay-data="{field:'classId', width:80}">ID</th>
				<th lay-data="{field:'departmentName', width:180}">学院名称</th>
				<th lay-data="{field:'grade', width:120}">年级</th>
				<th lay-data="{field:'profession', width:180, sort: true}">专业</th>
				<th lay-data="{field:'classNumber', width:120}">班级号</th>
			</tr>
		</thead>
	</table>

	<br>
	<div class="layui-btn-group demoTable">
		<button class="layui-btn" data-type="addManaged">增加管理班级</button>
	</div>
	<table class="layui-table" id="nclist"></table>


	<script src="${ctx}/layui/layui.js"></script>

	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#nclist',
				id : "nclist",
				url : '${ctx }/admin/publicer/notInManagedVo?publicerId=${publicer.publicerId }',
				cols : [ [ {
					checkbox : true
				}, {
					field : 'classId',
					width : 80,
					title : "ID"
				}, {
					field : 'departmentName',
					width : 180,
					title : "学院名称"
				}, {
					field : 'grade',
					width : 120,
					title : "学院名称"
				}, {
					field : 'profession',
					width : 180,
					title : "专业"
				}, {
					field : 'classNumber',
					width : 120,
					title : "班级号"
				}

				] ],
				page : false
			});

			var $ = layui.$, active = {
				delManaged : function() { //获取选中数据
					var checkStatus = table.checkStatus('clist'), data = checkStatus.data;
					var url = "${ctx}/admin/publicer/delManaged";

					dealIds(data, url);
				},
				addManaged : function() { //获取选中数据
					var checkStatus = table.checkStatus('nclist'), data = checkStatus.data;
					var url = "${ctx}/admin/publicer/addManaged";
					dealIds(data, url);

				}
			}
			$('.demoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});

			var reloadTable = function() {
				table.reload("clist");
				table.reload("nclist");
			}
			var dealIds = function(data, url) {
				if (data.length == 0) {
					return false;
				}
				;
				var ids = [];
				$.each(data, function(index, item) {
					ids.push(item.classId);
				})
				$.ajax({
					type : 'POST',
					url : url,
					traditional : true,
					data : {
						"ids" : ids,
						"publicerId" : "${publicer.publicerId}"
					},
					dataType : 'json',
					success : function(data) {

						if (data.code == 100) {
							reloadTable();
						} else {
							alert(JSON.stringify(data));
						}
					},
					error : function(data) {
						layer.msg(JSON.stringify(data));
					}
				});
			}
		});
	</script>

</body>
</html>