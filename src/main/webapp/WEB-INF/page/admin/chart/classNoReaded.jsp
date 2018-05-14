<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未读统计</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<style type="text/css">
.layui-row {
	padding-top: 20px;
}
</style>
</head>
<body>
	<div class="layui-container">
		<div class="layui-row">
			<h3>
				班级未读统计 - 前 <select style="display: inline-block;" id="limit">
					<option>10</option>
					<option>20</option>
					<option>30</option>
					<option>50</option>
					<option>100</option>
				</select> 名
			</h3>



			<table class="layui-table">
				<thead>
					<tr>
						<th>编号</th>
						<th>院系</th>
						<th>班级</th>
						<th>未读通知数量</th>
						<th>学生数量</th>
						<th>生均未读数量</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'jquery', 'layer' ], function() {
			var $ = layui.jquery, layer = layui.layer;

			var loadData = function() {
				var limit = $("#limit").val();
				$.get("${ctx}/charts/noReadedTopNClass?limit=" + limit, function(data) {
					var html = '';

					$.each(data.map.noReadedTopNStudent, function(index, item) {
						html += '<tr><td>' + (index + 1) + '</td><td>' + item.departmentName + '</td><td>' + item.grade + item.profession + item.classNumber + '班</td><td>' + item.count + '</td><td>' + item.studentNum + '</td><td>' + item.unreadRate + '</td></tr>';
					})
					$('tbody').html('');
					$('tbody').append(html);
				});
			}

			$(function() {
				loadData();
			})
			$('#limit').change(function() {
				loadData();
			});
		});
	</script>
</body>
</html>