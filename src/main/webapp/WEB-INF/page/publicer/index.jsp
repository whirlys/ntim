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
<title>NTIM 发布者端</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/app.css" media="all">
<script src="${ctx }/js/proto/message.js"></script>
<script src="${ctx }/js/proto/messageBody.js"></script>
<script type="text/javascript"
	src="${ctx }/js/common/websocketConfig.js"></script>
<script type="text/javascript" src="${ctx }/js/common/util.js"></script>
</head>

<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">NTIM 发布者端</div>
			<div class="layui-logo kit-logo-mobile">K</div>

			<ul class="layui-nav layui-layout-right kit-nav">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="${sessionScope.user.avatar }" class="layui-nav-img">
						${sessionScope.user.username }
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:;">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="${ctx }/logout"><i
						class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black kit-side">
			<div class="layui-side-scroll">
				<div class="kit-side-fold">
					<i class="fa fa-navicon" aria-hidden="true"></i>
				</div>
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar"
					kit-navbar>
					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/publicer/toNotices',icon:'&#xe658;',title:'通知管理',id:'1'}"><i
							class="layui-icon">&#xe658;</i><span> 通知管理</span></a></li>

					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/publicer/toForms',icon:'&#xe658;',title:'表单管理',id:'2'}"><i
							class="layui-icon">&#xe658;</i><span> 表单管理</span></a></li>
					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/publicer/toFiles',icon:'&#xe658;',title:'文件管理',id:'3'}"><i
							class="layui-icon">&#xe658;</i><span> 文件管理</span></a></li>
					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/question',icon:'&#xe658;',title:'问答专区',id:'4'}"><i
							class="layui-icon">&#xe658;</i><span> 问答专区</span></a></li>
				</ul>
			</div>
		</div>
		<div class="layui-body" id="container">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">主体内容加载中,请稍等...</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			2018 &copy; 赖键锋 大学消息通知系统
		</div>
	</div>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		var message;
		layui.config({
			base : '${ctx}/js/publicer/'
		}).use([ 'app', 'message' ], function() {
			var app = layui.app, $ = layui.jquery, layer = layui.layer;
			//将message设置为全局以便子页面调用
			message = layui.message;
			//主入口
			app.set({
				type : 'iframe'
			}).init();

		});
	</script>

	<!-- 配置Layim -->
	<script>
		/* var currentsession = "${jsessionid}"; */
		var currentsession = "${sessionScope.user.userId}";

		layui.use([ 'layer', 'jquery', 'layim' ], function() {
			var layer = layui.layer, $ = layui.jquery, layim = layui.layim;

			//发送消息
			var sendMsg = function(msg, receiver, group) {
				var message = new proto.Model();
				var content = new proto.MessageBody();

				message.setMsgtype(4);
				message.setCmd(5);
				message.setGroupid(group);//系统用户组
				message.setToken(currentsession);
				message.setSender(currentsession);
				message.setReceiver(receiver);//好友ID
				content.setContent(msg);
				content.setType(0)
				message.setContent(content.serializeBinary())

				socket.send(message.serializeBinary());
			}

			var showmsg = function(data) {
				var msg = eval("(" + data.user + ")");
				var content = eval("(" + data.content + ")");
				var cache = layui.layim.cache();
				var local = layui.data('layim')[cache.mine.id];
				var username = "", avatar = "", friend = false;
				layui.each(cache.friend, function(index1, item1) {
					layui.each(item1.list, function(index, item) {
						if (item.id == msg.sender) {
							username = item.username;
							avatar = item.avatar;
							return friend = true;
						}
					});
					if (friend)
						return true;
				});

				if (msg.cmd == 3) {
					if (msg.sender != currentsession) {
						//layer.msg(username + "上线了");
						//layim.setFriendStatus(msg.getSender(), 'online');
					}
				} else if (msg.cmd == 4) {
					if (msg.sender != currentsession) {
						//layer.msg(username + "下线了");
						//layim.setFriendStatus(msg.getSender(), 'offline');
					}
				} else if (msg.cmd == 5) {
					//显示非自身消息    
					if (msg.sender != currentsession) {
						var time = (new Date(msg.timeStamp)).getTime();
						//不显示用户组消息
						if (msg.groupId == null || msg.groupId.length < 1) {
							layim.getMessage({
								username : username,
								avatar : avatar,
								id : msg.sender,
								type : "friend",
								content : content.content,
								timestamp : time
							});
						} else {
							layim.getMessage({
								username : username,
								avatar : avatar,
								id : msg.groupId,
								type : "group",
								content : content.content,
								timestamp : time
							});
						}
					}
				}
			}

			//基础配置
			layim.config({
				title : "我的LayIM",
				right : "50px", //设定主面板右偏移量
				isAudio : false, //是否开启聊天工具栏音频
				isVideo : false,//是否开启开启聊天工具栏视频
				notice : true, //是否开启桌面消息提醒，即在浏览器之外的提醒
				copyright : true, // 版权提示
				/*voice:,  */
				init : {//获取主面板列表信息，下文会做进一步介绍
					url : '${ctx}/publicer/layim/init',//接口地址（返回的数据格式见下文）

					type : 'get' //默认get，一般可不填
					,
					data : {}
				//额外参数
				},

				//获取群员接口（返回的数据格式见下文）
				members : {
					url : '${ctx}/publicer/layim/members' //接口地址（返回的数据格式见下文）
					,
					type : 'get' //默认get，一般可不填
					,
					data : {}
				//额外参数
				}

				//上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
				,
				uploadImage : {
					url : '${ctx}/upload/im/images' //接口地址
					,
					type : 'post' //默认post
				}

				//上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
				,
				uploadFile : {
					url : '${ctx}/upload/im/files', //接口地址
					acceptMime : "txt"
				}
				//扩展工具栏，
				/* ,
				tool : [ {
					alias : 'code' //工具别名
					,
					title : '代码' //工具名称
					,
					icon : '&#xe64e;' //工具图标，参考图标文档
				} ] */

				,
				msgbox : layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
				,
				find : layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
				,
				chatLog : '${ctx}/layim/chatlog' //聊天记录页面地址，若不开启，剔除该项即可
			});

			//监听发送消息
			layim.on('sendMessage', function(data) {
				var To = data.to;
				var my = data.mine;
				var message = my.content;
				var receiver = To.id + "";

				if ($.trim(currentsession) == '') {
					return;
				}
				if ($.trim(message) == '') {
					layer.msg("请输入要发送的消息!");
					return;
				}
				if (!window.WebSocket) {
					alert("本浏览器不支持websocket， 发送消息失败！")
					return;
				} else {

					if (socket.readyState == WebSocket.OPEN) {

						//判断是发送好友消息还是群消息
						if (To.type == "friend") {
							sendMsg(message, receiver, null)
						} else {
							sendMsg(message, null, receiver)
						}
					}
				}

			});
			layim.on('sign', function(value) {
				$.ajax({
					url : "${ctx }/layim/editSign",
					data : {
						"sign" : value
					},
					method : "post",
					dataType : 'json',
					success : function(data) {
						if (data['code'] == 100) {
						} else {
							layer.alert(JSON.stringify(data))
						}
					},
					error : function(xhr, textStatus) {

					},
				});
				//此时，你就可以通过Ajax将新的签名同步到数据库中了。
			});

			layim.on('online', function(status) {
				//console.log(status); //获得online或者hide
				//websocket发送在线或离线消息给好友
			});
			//拉取离线消息
			var showOfflineMsg = function(layim) {
				$.ajax({
					type : "post",
					url : "${ctx}/layim/getofflinemsg",
					async : true,
					success : function(data) {
						if (data.code == 100) {
							var msgs = data.map.offlineMsg;
							if (msgs != null && msgs.length > 0) {
								for (var i = 0; i < msgs.length; i++) {
									layim.getMessage({
										username : msgs[i].username,
										avatar : msgs[i].avatar,
										id : msgs[i].id,
										type : "friend",
										content : msgs[i].content,
										timestamp : msgs[i].timestamp
									});
								}
							}
						}

					}
				});
			}
			layim.on('ready', function(res) {

				//取得离线消息
				showOfflineMsg(layim);
				//layim.setFriendStatus(currentsession, 'online');
			});
			var initEventHandle = function() {
				//收到消息后
				socket.onmessage = function(event) {
					if (event.data instanceof ArrayBuffer) {
						var msg = proto.Model.deserializeBinary(event.data); //如果后端发送的是二进制帧（protobuf）会收到前面定义的类型
						var msgCon = proto.MessageBody.deserializeBinary(msg.getContent());
						var cache = layui.layim.cache();
						var local = layui.data('layim')[cache.mine.id];
						var username = "", avatar = "", friend = false;
						layui.each(cache.friend, function(index1, item1) {
							layui.each(item1.list, function(index, item) {
								if (item.id == msg.getSender()) {
									username = item.username;
									avatar = item.avatar;
									return friend = true;
								}
							});
							if (friend)
								return true;
						});

						//心跳消息
						if (msg.getCmd() == 2) {
							//发送心跳回应
							var message1 = new proto.Model();
							message1.setCmd(2);
							message1.setMsgtype(4);
							socket.send(message1.serializeBinary());
						} else if (msg.getCmd() == 3) {
							//上线
							if (msg.getSender() != currentsession) {
								//layer.msg(username + "上线了！");
								//layim.setFriendStatus(msg.getSender(), 'online');
							}
						} else if (msg.getCmd() == 4) {
							//下线
							if (msg.getSender() != currentsession) {
								//layer.msg(username + "已下线！");
								//layim.setFriendStatus(msg.getSender(), 'offline');
							}
						} else {
							//显示非自身消息    
							if (msg.getSender() != currentsession) {
								//不显示用户组消息

								var time = (new Date(msg.getTimestamp())).getTime();
								if (msg.getGroupid() == null || msg.getGroupid().length < 1) {
									layim.getMessage({
										username : username,
										avatar : avatar,
										id : msg.getSender(),
										type : "friend",
										content : msgCon.getContent(),
										timestamp : time
									});
								} else {
									layim.getMessage({
										username : username,
										avatar : avatar,
										id : msg.getGroupid(),
										type : "group",
										content : msgCon.getContent(),
										timestamp : time
									});
								}
							}
						}
					} else {
						var data = event.data; //后端返回的是文本帧时触发
					}
				};
				//连接后
				socket.onopen = function(event) {
					var message = new proto.Model();
					var browser = BrowserUtil.info();
					message.setVersion("1.0");
					message.setDeviceid("")
					message.setCmd(1);
					message.setSender(currentsession);
					message.setMsgtype(1);
					message.setFlag(1);
					message.setPlatform(browser.name);
					message.setPlatformversion(browser.version);
					message.setToken(currentsession);
					var bytes = message.serializeBinary();
					socket.send(bytes);

				};
				//连接关闭
				socket.onclose = function(event) {
					layim.setFriendStatus(currentsession, 'offline');
					layer.confirm('您已下线，重新上线?', function(index) {
						reconnect(websocketurl, initEventHandle);
						layer.close(index);
					});
				};
				socket.onerror = function() {
					reconnect(websocketurl, initEventHandle);
				};
			}

			createWebSocket(websocketurl, initEventHandle);
		});
	</script>
</body>

</html>