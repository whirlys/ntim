layui.use('form', function() {
	var form = layui.form, $ = layui.jquery, layer = layui.layer;

	// 监听提交
	form.on('submit(data-form)', function(data) {
		var url = $(".layui-form").data("action");

		$.ajax({
			type : 'POST',
			url : url,
			data : data.field,
			dataType : 'json',
			success : function(data) {
				if (data.code == 100) {
					layer.msg(data.msg);
					layer.closeAll("iframe");
					// 刷新父页面
					parent.location.reload();
				} else {
					layer.msg(data.map.error);
				}
			},
			error : function(data) {
				layer.msg(JSON.stringify(data));
			}
		});

		return false;
	});
});