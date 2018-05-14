layui.use([ 'form', 'layer', 'laydate', 'table', 'laytpl' ],function() {
	var form = layui.form, layer = layui.layer, 
			$ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table;

	
	// 监听提交
	  form.on('submit(search)', function(data){
		  table.reload('list-table', {
			  where: data.field
			});
	  });
	  
	  // 添加
	  $(".add-btn").click(function(){
		  var url = $(this).data("url");
		  var title = $(this).data("title");
		  var index = layui.layer.open({
				title : title,
				type : 2,
				content : url,
				success : function(layero, index) {

				}
			})
			layui.layer.full(index);
			//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(index);
			})
	  });
	  
	  // 监听工具条 编辑
	  table.on('tool(list)', function(obj){
	    var data = obj.data; 
	    var layEvent = obj.event;
	    var tr = obj.tr;
	   alert(JSON.stringify(data));
	    if(layEvent === 'detail'){ //详情
		    
		}
	    else if(layEvent === 'del'){ //删除
	      layer.confirm('真的删除行么', function(index){
	        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	        layer.close(index);
	        //向服务端发送删除指令
	      });
	    } else if(layEvent === 'edit'){ //编辑
	    	var url = $("#edit").data("url");
	    	var title = $("#edit").data("title");
	    	var index = layui.layer.open({
				title : title,
				type : 2,
				content : url,
				success : function(layero, index) {
				}
			})
			layui.layer.full(index);
			//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(index);
			})
	    } else if(layEvent == "managed"){
	    	// 管理班级
	    	var url = $("#managed").data("url");
	    	var title = $("#managed").data("title");
	    	alert(url + title);
	    	var index = layui.layer.open({
				title : title,
				type : 2,
				content : url,
				success : function(layero, index) {
					
				}
			})
			layui.layer.full(index);
			//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			/*$(window).on("resize", function() {
				layui.layer.full(index);
			})*/
	    }
	  });
	  
})