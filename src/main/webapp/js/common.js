/*

@Name：yxg
@Author：zcc
@Site：http://www.cczblog.cn

*/


var commonAjax={
    error:function( XMLHttpRequest,layer) {
        console.log(XMLHttpRequest.status);
        layer.msg(6);
        if(XMLHttpRequest.status==413){
            layer.msg('您未拥有相关权限');
        }else{
            layer.alert("系统异常",{icon: 5});
        }
    },
}

