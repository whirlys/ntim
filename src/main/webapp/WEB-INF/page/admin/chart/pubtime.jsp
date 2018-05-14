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

		<div id="chart" style="width: 1000px; height: 500px;"></div>

	</div>
	<script type="text/javascript">
		var option = {
			title: {
				text: "通知发布时间统计折线图",
				padding : 15
			},
			dataZoom: [
		        {   // 这个dataZoom组件，默认控制x轴。
		            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
		            start: 0,      // 左边在 0% 的位置。
		            end: 100         // 右边在 100% 的位置。
		        }
		    ],
			xAxis : {
				type : 'category',
				data : []
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				data : [],
				type : 'line',
				symbol : 'triangle',
				symbolSize : 20,
				
				lineStyle : {
					normal : {
						color : 'green',
						width : 4,
						type : 'dashed'
					}
				},
				itemStyle : {
					normal : {
						borderWidth : 3,
						borderColor : 'yellow',
						color : 'blue',
						label : {
							show : true,
							position : 'top'
						}
					}
				}
			} ]
		};

		Date.prototype.addDays = function(days) {
			var dat = new Date(this.valueOf())
			dat.setDate(dat.getDate() + days);
			return dat;
		}
		//格式化日期
		Date.prototype.Format = function (fmt) {
		  var o = {
		    "y+": this.getFullYear(),
		    "M+": this.getMonth() + 1,                 //月份
		    "d+": this.getDate(),                    //日
		    "h+": this.getHours(),                   //小时
		    "m+": this.getMinutes(),                 //分
		    "s+": this.getSeconds(),                 //秒
		    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
		    "S+": this.getMilliseconds()             //毫秒
		  };
		  for (var k in o) {
		    if (new RegExp("(" + k + ")").test(fmt)){
		      if(k == "y+"){
		        fmt = fmt.replace(RegExp.$1, ("" + o[k]).substr(4 - RegExp.$1.length));
		      }
		      else if(k=="S+"){
		        var lens = RegExp.$1.length;
		        lens = lens==1?3:lens;
		        fmt = fmt.replace(RegExp.$1, ("00" + o[k]).substr(("" + o[k]).length - 1,lens));
		      }
		      else{
		        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		      }
		    }
		  }
		  return fmt;
		}

		function getDates(startDate, stopDate) {
			var dateArray = new Array();
			var currentDate = startDate;
			while (currentDate <= stopDate) {
				var date = new Date(currentDate);
				var item = {}
				item['date'] = date.Format("yyyy-MM-dd")
				item['count'] = 0;
				dateArray.push(item)
				currentDate = currentDate.addDays(1);
			}
			return dateArray;
		}
		$.get("${ctx}/charts/countPubTime",function(data){
			
			var pubTimeData = data.map.pubTime;
			console.log(pubTimeData)
			var startTime = pubTimeData[0].datetime.split('-')
			var endTime = pubTimeData[pubTimeData.length - 1].datetime.split('-')
			var startDate = new Date(startTime[0], startTime[1], startTime[2]);
			var endDate = new Date(endTime[0], endTime[1], endTime[2]);
	
			var dates = getDates(new Date(startDate.getFullYear(), startDate.getMonth()-1, startDate.getDate()), new Date(endDate.getFullYear(), endDate.getMonth()-1, endDate.getDate()));
			
			var i = 0, j = 0;
			while(i<pubTimeData.length && j < dates.length){
				if(pubTimeData[i].datetime == dates[j].date){
					dates[j].count = pubTimeData[i].count;
					i = i +1 ;
					j=j+1;
				}else{
					j = j+1;
				}
			}
			$.each(dates, function(index, item){
				option.xAxis.data.push(item.date)
				option.series[0].data.push(item.count)
			})
			var chart = echarts.init(document.getElementById('chart'));
			chart.setOption(option)
		}) 
		
	</script>

</body>
</html>