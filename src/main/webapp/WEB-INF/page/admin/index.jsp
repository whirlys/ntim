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
<title>NTIM 系统管理后台</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/app.css" media="all">
</head>

<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">NTIM 管理后台</div>
			<div class="layui-logo kit-logo-mobile">K</div>

			<ul class="layui-nav layui-layout-right kit-nav">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="${ sessionScope.user.avatar }" class="layui-nav-img">
						${ sessionScope.user.username }
				</a></li>
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
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe658;</i><span> 用户管理</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/admin/departmentlist',icon:'&#xe6c6;',title:'院系列表',id:'1'}">
									<i class="layui-icon">&#xe6c6;</i><span> 院系列表</span>
								</a>
							</dd>
							<dd>
								<a href="javascript:;" data-url="${ctx }/admin/getclasss"
									data-icon="&#xe6c6;" data-title="班级列表" kit-target data-id='2'><i
									class="layui-icon">&#xe6c6;</i><span><span> 班级列表</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-url="${ctx }/admin/stulist" data-icon="fa-user"
									data-title="学生列表" kit-target data-id='3'><i
									class="layui-icon">&#xe6c6;</i><span><span> 学生列表</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/admin/publicer/index',icon:'&#xe6c6;',title:'发布者列表',id:'4'}">
									<i class="layui-icon">&#xe6c6;</i><span> 发布者列表</span>
								</a>
							</dd>
						</dl></li>

					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/admin/notice',icon:'&#xe658;',title:'通知列表',id:'5'}"><i
							class="layui-icon">&#xe658;</i><span> 通知管理</span></a></li>

					<li class="layui-nav-item"><a href="javascript:;" kit-target
						data-options="{url:'${ctx }/admin/form',icon:'&#xe658;',title:'表单管理',id:'6'}"><i
							class="layui-icon">&#xe658;</i><span> 表单管理</span></a></li>

					<li class="layui-nav-item"><a href="javascript:;"><i
							class="layui-icon">&#xe658;</i><span> 数据统计</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/users',icon:'&#xe658;',title:'用户统计',id:'12'}"><i
									class="layui-icon">&#xe658;</i><span> 用户统计</span></a>
							</dd>

							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/pubtime',icon:'&#xe658;',title:'时间统计',id:'14'}"><i
									class="layui-icon">&#xe658;</i><span> 发布时间</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/notices',icon:'&#xe658;',title:'通知统计',id:'15'}"><i
									class="layui-icon">&#xe658;</i><span> 通知统计</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/types',icon:'&#xe658;',title:'类型统计',id:'16'}"><i
									class="layui-icon">&#xe658;</i><span> 类型统计</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/noReaded',icon:'&#xe658;',title:'学生未读统计',id:'17'}"><i
									class="layui-icon">&#xe658;</i><span> 学生未读</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'${ctx }/charts/classNoReaded',icon:'&#xe658;',title:'班级未读统计',id:'18'}"><i
									class="layui-icon">&#xe658;</i><span> 班级未读</span></a>
							</dd>
						</dl></li>

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
	<!-- <script type="text/javascript">
		var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='"
						+ cnzz_protocol
						+ "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
	</script> -->
	<script src="${ctx}/layui/layui.js"></script>
	<script>
		var message;
		layui.config({
			base : '${ctx}/js/'
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
</body>

</html>