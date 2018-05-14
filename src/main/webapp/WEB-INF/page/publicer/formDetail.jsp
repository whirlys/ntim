<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>表单数据</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css"
	media="all">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
<script type="text/javascript"
	src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
<style type="text/css">
.am-article-hd {
	text-align: center;
}

.am-article-meta p {
	text-align: center;
}

.am-article {
	margin-bottom: 20px;
}

.readInfo {
	display: inline-block;
	font-size: 12px;
	float: right;
}

.readInfo span {
	color: orange;
}
</style>
</head>
<body>
	<div class="layui-container">
		<article class="am-article">
			<div class="am-article-hd">
				<h1 class="am-article-title">${form.formName }</h1>
				<p class="am-article-meta">
					发布时间：
					<fmt:formatDate value="${form.createtime}"
						pattern="yyyy-MM-dd HH:mm" />
					截止时间： ${form.formatDeadline }
				</p>
			</div>

			<div class="am-article-bd">
				<p class="am-article-lead">${form.description }</p>
			</div>
		</article>

		<legend>
			已收集的数据
			<div class="readInfo">
				<a href="${ctx }/publicer/toExcel/${form.formId}" target="_blank"
					class="layui-btn layui-btn-xs layui-btn-normal"> 下载Excel </a> 共 <span>${readNum + notReadNum }</span>
				人 <span>${notReadNum }</span> 人未填 <a id="showReceiver"
					class="layui-btn layui-btn-xs layui-btn-warm" href="javascript:;">查看</a>
				<a id="urge" class="layui-btn layui-btn-xs layui-btn-danger"
					href="javascript:;">催促</a>
			</div>
		</legend>
		<div>
			<table class="am-table am-table-hover am-table-striped">
				<thead>
					<tr>
						<th>班级</th>
						<th>姓名</th>
						<c:forEach items="${form.fields }" var="field">
							<th>${field.name }</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${records }" var="record">
						<tr>
							<td>${record.classs.className }</td>
							<td>${record.username }</td>
							<c:forEach items="${form.fields }" var="field">
								<td class="am-warning">${record.valueMap[field.name] }</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script src="${ctx}/layui/layui.js"></script>
	<script>
		//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'layer', 'jquery' ], function() {
			var element = layui.element, layer = layui.layer, $ = layui.jquery;

			$('#showReceiver').click(function() {
				layer.open({
					title : "表单填写情况",
					area : [ '800px', '500px' ],
					shadeClose : true,
					type : 2,
					content : '${ctx}/publicer/showReceiverForm/${ form.formId}'
				});
			});

			$('#urge').click(function() {
				layer.alert("已通过email或短信催促未填表单的学生填写表单！")
			});
		});
	</script>
</body>
</html>