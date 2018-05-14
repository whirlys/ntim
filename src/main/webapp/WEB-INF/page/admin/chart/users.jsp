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
<title>用户统计</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/echarts/echarts.common.min.js"></script>
</head>
<body>
	<div class="layui-container">

		<div id="studentChart" style="width: 1000px; height: 400px;"></div>
		<div id="publicerChart" style="width: 1000px; height: 400px;"></div>



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
		$.get("${ctx}/charts/countUsers", function(data) {

			buildStudentChart(data);
			buildPublicerChart(data);
		});
		var buildStudentChart = function(data) {
			var studentChart = echarts.init(document.getElementById('studentChart'));
			var usersOption = JSON.parse(JSON.stringify(optionExample));
			var total = 0;
			$.each(data.map.users, function(index, item) {
				usersOption.xAxis.data.push(item.departmentName);
				usersOption.series[0].data.push(item.count);
				total = total + item.count;
			});
			usersOption.title.text = '各院系学生人数 - ' + '总（' + total + '）';
			usersOption.legend.data.push('人数')
			studentChart.setOption(usersOption);
		}
		var buildPublicerChart = function(data) {
			var publicerChart = echarts.init(document.getElementById('publicerChart'));
			var option = JSON.parse(JSON.stringify(optionExample));
			var total = 0;
			$.each(data.map.publicers, function(index, item) {
				option.xAxis.data.push(item.departmentName);
				option.series[0].data.push(item.count);
				total = total + item.count;
			});
			option.title.text = '各院系发布者人数 - ' + '总（' + total + '）';
			option.legend.data.push('人数')
			publicerChart.setOption(option);
		}
	</script>

</body>
</html>