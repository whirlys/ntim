
layui.use(['element', 'layer', 'util', 'form','carousel'], function () {
    var $ = layui.jquery;
    var layer=layui.layer;
    var carousel = layui.carousel;
    $(function(){
    	$("#menuBar").on("click",function(){
    		 var oClass=$(".layui-side").hasClass("mini");

    		 if(!oClass){
    			 $(".layui-side").animate({
        			 width:"50px"
        		 })
        		 $(".layui-body").animate({
        			 left:"50"
        		 })
        		  $(".layui-footer").animate({
        			 left:"50"
        		 })

    		 }else{
    			 $(".layui-side").animate({
        			 width:"200px"
        		 })
        		  $(".layui-body").animate({
        			 left:"200"
        		 }) 
        	     $(".layui-footer").animate({
                     left:"200"
        		 })

    		 }
            $(".layui-side").toggleClass("mini");

    	});
    });
    
    
    
    
});