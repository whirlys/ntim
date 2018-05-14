<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>主面板</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/font-awesome.min.css">
<style>
.info-box {
	height: 85px;
	background-color: white;
	background-color: #ecf0f5;
}

.info-box .info-box-icon {
	border-top-left-radius: 2px;
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 2px;
	display: block;
	float: left;
	height: 85px;
	width: 85px;
	text-align: center;
	font-size: 45px;
	line-height: 85px;
	background: rgba(0, 0, 0, 0.2);
}

.info-box .info-box-content {
	padding: 5px 10px;
	margin-left: 85px;
}

.info-box .info-box-content .info-box-text {
	display: block;
	font-size: 14px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	text-transform: uppercase;
}

.info-box .info-box-content .info-box-number {
	display: block;
	font-weight: bold;
	font-size: 18px;
}

.major {
	font-weight: 10px;
	color: #01AAED;
}

.main {
	margin-top: 25px;
}

.main .layui-row {
	margin: 10px 0;
}
</style>
</head>

<body>
	<div class="layui-fluid main">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md3">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #00c0ef !important; color: white;"><i
						class="fa fa-users" aria-hidden="true"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">学生总数</span> <span
							class="info-box-number">${studentNum }</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #dd4b39 !important; color: white;"><i
						class="fa fa-user-o" aria-hidden="true"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">发布者总数</span> <span
							class="info-box-number">${publicerNum }</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #00a65a !important; color: white;"><i
						class="fa fa-bell-o" aria-hidden="true"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">通知总数</span> <span
							class="info-box-number">${noticeNum }</span>
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="info-box">
					<span class="info-box-icon"
						style="background-color: #f39c12 !important; color: white;"><i
						class="fa fa-table" aria-hidden="true"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">表单总数</span> <span
							class="info-box-number">${formNum }</span>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<ul class="layui-timeline">
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">体系架构图</h3>

							<ul>
								<li><img src="${ctx }/images/system.png"></li>
							</ul>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">后续进度安排</h3>
							<ul>
								<li>第4周：对消息通知，单聊和群聊，文件管理等核心功能进行开发和测试</li>
								<li>第5周：对数据统计报表，动态表单，简单问答机器人等功能进行开发和测试</li>
								<li>第6周：进行各种测试，并且不断对系统进行调优，使系统完善，撰写论文</li>
								<li>第7周：论文修改和系统性能改进提升</li>
							</ul>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">2月25日</h3>
							<p>
							<h3>ER图</h3>
							<ul>
								<li><img src="${ctx }/images/3.png"></li>
							</ul>
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">1月31日</h3>
							<p>
							<h3>使用的技术</h3>
							<ul>
								<li>spring+springmvc+mybatis</li>
								<li>mysql,redis</li>
								<li>netty</li>
								<li>layui</li>
							</ul>

							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">2018年</div>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">12月30日</h3>
							<p>
							<h3>系统的功能</h3>
							<ul>
								<li>1、实现用户管理模块，管理员可方便地管理各种系统角色和关系</li>
								<li>2、实现消息通知模块，可指定接收通知的用户，确保通知能够被消息接收者接收，且一条通知有一个对应的讨论组，消息发布者也可以补充通知说明</li>
								<li>3、实现单聊和群聊模块，确保消息发布者与消息接收者之间能够方便地进行沟通</li>
								<li>4、实现文件管理模块，确保上传到系统的文件能够检索，下载</li>
								<li>5、实现动态表格模块，确保消息发布者能够方便的建立动态的表格并下发给消息接收者填写</li>
								<li>6、实现数据统计和报表模块，对消息接收情况进行统计，并以报表的形式呈现统计结果</li>
								<li>7、实现简单问答机器人模块，可向用户提供简单的问答服务</li>
							</ul>

							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">12月29日</h3>
							<p>
							<h3>新的消息通知模式</h3>
							<ul>

								<li><img src="${ctx }/images/2.png"></li>
								<li><h4>本系统的作用</h4></li>
								<li>减轻班长的负担，提高同学与学校，学院，辅导员，社团学生会或其他之间的沟通效率.</li>
								<li>来减轻班长的负担，提高同学与学校，学院，辅导员，社团学生会或其他之间的沟通效率</li>

							</ul>
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">12月26日</h3>
							<p>
							<h3>目前的消息通知模式</h3>
							<ul>

								<li><img src="${ctx }/images/1.png"></li>
								<li><h4>存在的问题：</h4>
								<li>
								<li>1.
									对各班长来说：几乎所有的消息都要通过班长来传达给班里同学，这个负担还是有点大的，当各种事情叠加在一起时可能存在通知延时，遗漏，传达不准确的情况。</li>
								<li>2. QQ群，微信群里，经常是通知和问答都混合在一起，导致要找某条通知时找不到，或者遗漏的问题</li>
								<li>3.
									对教务员，辅导员和学生助理来说：当每个班对一条通知有疑问时，就会重复的回答同一个问题；学生助理经常是忙碌于对学生信息的收集，检查，统计的工作中</li>
							</ul>
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">12月24日</h3>
							<p>
							<h3>即时沟通工具</h3>
							<ul>

								<li>1.阿里钉钉 2.微信，微信企业版 3.QQ，TIM 4.第一企信
									5.网易即时通、超享工作、纷享销客、销售易、外勤365、红圈营销、叮当享等</li>
								<li>上面的这些产品的功能一般都有：
									工作流、CRM、企业学习、财务报销、考勤、报销、外勤、云盘、任务、日志、考试测评、人资管理，企业组织架构，离线留言，企业分支互联，文本消息对话，语音交流，多人语音会议，文件传输，网络电话等
								</li>
								<li>但是上述工具基本都是适合于企业或团队之间的协同办公和沟通，而不适合与大学组织与学生之间的消息传达</li>
							</ul>
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">2017年</div>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">更新日志</div>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
	<script src="${ctx}/layui/layui.js"></script>
	<script>
		layui.use('jquery', function() {
			var $ = layui.jquery;
			$('#test').on('click', function() {
				parent.message.show({
					skin : 'cyan'
				});
			});
		});
	</script>
</body>

</html>