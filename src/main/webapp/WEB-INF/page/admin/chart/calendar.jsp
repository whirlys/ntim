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
<script type="text/javascript" src="${ctx }/echarts/echarts.min.js"></script>
</head>
<body>
	<div class="layui-container">
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div id="main" style="width: 1100px; height: 600px;"></div>

	</div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 模拟数据
		function getVirtulData(year) {
			year = year || '2017';
			var date = +echarts.number.parseDate(year + '-01-01');
			var end = +echarts.number.parseDate((+year + 1) + '-01-01');
			var dayTime = 3600 * 24 * 1000;
			var data = [];
			for (var time = date; time < end; time += dayTime) {
				data.push([ echarts.format.formatTime('yyyy-MM-dd', time), Math.floor(Math.random() * 10000) ]);
			}
			return data;
		}

		var data = getVirtulData(2016);

		option = {
			backgroundColor : '#404a59',

			title : {
				top : 30,
				text : '通知日历图',
				left : 'center',
				textStyle : {
					color : '#fff'
				}
			},
			tooltip : {
				trigger : 'item'
			},
			legend : {
				top : '30',
				left : '100',
				data : [ '通知数量' ],
				textStyle : {
					color : '#fff'
				}
			},
			calendar : [ {
				top : 100,
				left : 'center',
				range : [ '2018-01-01', '2018-06-30' ],
				splitLine : {
					show : true,
					lineStyle : {
						color : '#000',
						width : 4,
						type : 'solid'
					}
				},
				yearLabel : {
					formatter : '{start}  1st',
					textStyle : {
						color : '#fff'
					}
				},
				itemStyle : {
					normal : {
						color : '#323c48',
						borderWidth : 1,
						borderColor : '#111'
					}
				}
			}, {
				top : 340,
				left : 'center',
				range : [ '2016-07-01', '2016-12-31' ],
				splitLine : {
					show : true,
					lineStyle : {
						color : '#000',
						width : 4,
						type : 'solid'
					}
				},
				yearLabel : {
					formatter : '{start}  2nd',
					textStyle : {
						color : '#fff'
					}
				},
				itemStyle : {
					normal : {
						color : '#323c48',
						borderWidth : 1,
						borderColor : '#111'
					}
				}
			} ],
			series : [ {
				name : '
		步数',
				type : 'scatter',
				coordinateSystem : 'calendar',
				data : data,
				symbolSize : function(val) {
					return val[1] / 500;
				},
				itemStyle : {
					normal : {
						color : '#ddb926'
					}
				}
			}, {
				name : '步数',
				type : 'scatter',
				coordinateSystem : 'calendar',
				calendarIndex : 1,
				data : data,
				symbolSize : function(val) {
					return val[1] / 500;
				},
				itemStyle : {
					normal : {
						color : '#ddb926'
					}
				}
			}, {
				name : 'Top 12',
				type : 'effectScatter',
				coordinateSystem : 'calendar',
				calendarIndex : 1,
				data : data.sort(function(a, b) {
					return b[1] - a[1];
				}).slice(0, 12),
				symbolSize : function(val) {
					return val[1] / 500;
				},
				showEffectOn : 'render',
				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				itemStyle : {
					normal : {
						color : '#f4e925',
						shadowBlur : 10,
						shadowColor : '#333'
					}
				},
				zlevel : 1
			}, {
				name : 'Top 12',
				type : 'effectScatter',
				coordinateSystem : 'calendar',
				data : data.sort(function(a, b) {
					return b[1] - a[1];
				}).slice(0, 12),
				symbolSize : function(val) {
					return val[1] / 500;
				},
				showEffectOn : 'render',
				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				itemStyle : {
					normal : {
						color : '#f4e925',
						shadowBlur : 10,
						shadowColor : '#333'
					}
				},
				zlevel : 1
			} ]
		};

		myChart.setOption(option);
	</script>

</body>
</html>