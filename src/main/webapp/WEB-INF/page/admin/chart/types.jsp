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
<title>类型统计</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/echarts/echarts.common.min.js"></script>
</head>
<body>
	<div class="layui-container">

		<div id="noticeByType" style="width: 100%; height: 500px;"></div>

	</div>
	<script type="text/javascript">
		// 指定图表的配置项和数据
		var optionExample = {
			backgroundColor : '#2c343c',

			title : {
				text : '通知类型统计',
				left : 'center',
				top : 20,
				textStyle : {
					color : '#ccc'
				}
			},

			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},

			visualMap : {
				show : false,
				min : 80,
				max : 600,
				inRange : {
					colorLightness : [ 0, 1 ]
				}
			},
			series : [ {
				name : '数量',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '50%' ],
				data : [],
				roseType : 'radius',
				label : {
					normal : {
						textStyle : {
							color : 'rgba(255, 255, 255, 0.3)'
						}
					}
				},
				labelLine : {
					normal : {
						lineStyle : {
							color : 'rgba(255, 255, 255, 0.3)'
						},
						smooth : 0.2,
						length : 10,
						length2 : 20
					}
				},

				animationType : 'scale',
				animationEasing : 'elasticOut',
				animationDelay : function(idx) {
					return Math.random() * 300;
				}
			} ]
		};
		var buildChart = function(data) {
			var chart = echarts.init(document.getElementById('noticeByType'));
			var option = JSON.parse(JSON.stringify(optionExample));

			$.each(data, function(index, item) {
				var d = {};
				d['value'] = item.count;
				d['name'] = item.name;
				option.series[0].data.push(d);
			})
			chart.setOption(option);
		}

		$.get("${ctx}/charts/typesCountData", function(data) {
			buildChart(data.map.noticeNumByTypes);
		});
	</script>

</body>
</html>