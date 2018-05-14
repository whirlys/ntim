<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>通知统计</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/echarts/echarts.common.min.js"></script>
</head>
<body>
	<div class="layui-container">

		<div id="noticeByDepartment" style="width: 1000px; height: 400px;"></div>

		<div>
			<h2>发布者通知发布数量排名 - 前15</h2>
			<table class="layui-table">
				<colgroup>
					<col>
					<col>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>院系</th>
						<th>姓名</th>
						<th>职务</th>
						<th>通知数量</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		// 指定图表的配置项和数据
		var optionExample = {
			title : {
				text : '各院系学生人数',
				padding : 15
			},
			tooltip : {},
			legend : {
				data : [],
				top : 20
			},
			xAxis : {

				type : 'category',
				data : [],
				axisLabel : {
					//坐标轴刻度标签的相关设置。
					interval : 0,
					rotate : "10"
				}
			},
			yAxis : {},
			series : [ {
				name : '人数',
				type : 'bar',
				data : [],
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top'
						}
					}
				}
			} ]
		};
		$.get("${ctx}/charts/noticesCountData", function(data) {
			buildStudentChart(data.map.noticeNumByDepartment);
			buildTable(data.map.noticeNumByPublicer);
		});
		var buildStudentChart = function(data) {
			var chart = echarts.init(document.getElementById('noticeByDepartment'));
			var option = JSON.parse(JSON.stringify(optionExample));
			var total = 0;
			$.each(data, function(index, item) {
				option.xAxis.data.push(item.name);
				option.series[0].data.push(item.count);
				total = total + item.count;
			});
			option.title.text = '各院系通知的数量 - ' + '总（' + total + '）';
			option.legend.data.push('通知数量')
			chart.setOption(option);
		}

		var buildTable = function(data) {
			var html = "";
			$.each(data, function(index, item) {
				html += '<tr><td>' + item.departmentName + '</td><td>' + item.name + '</td><td>' + item.duty + '</td><td>' + item.count + '</td></tr>';
			})
			$('tbody').append(html);
		}
	</script>

</body>
</html>