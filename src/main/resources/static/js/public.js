//封装的时间
function dateShow(fmt,date){
			  var o = {   
			    "M+" : date.getMonth()+1,                 // 月份
			    "d+" : date.getDate(),                    // 日
			    "h+" : date.getHours(),                   // 小时
			    "m+" : date.getMinutes(),                 // 分
			    "s+" : date.getSeconds(),                 // 秒
			    "q+" : Math.floor((date.getMonth()+3)/3), // 季度
			    "S"  : date.getMilliseconds()             // 毫秒
			  };   
			  if(/(y+)/.test(fmt))   
			    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
			    if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;   
			}

// 封装的透明弹框
function tankuang(msg){
	if(msg=='' || msg==null){
		msg='错误';
	}
	layer.msg(msg, {
        time: 1000, // 20s后自动关闭
      });
}
// 封装的透明弹框2
function isTK(data){
	if(data.success){
		if (data.isTan != '' && data.isTan != null) {
			tankuang(data.isTan);
		}
	}else{
		if (data.isTan != '' && data.isTan != null) {
			tankuang(data.isTan);
		} else {
			tankuang("操作失败");
		}
	}
}
// 封装的ajax
function ajax(url,params,success,async){
	var obj ={};
	$.each(params, function(index, field) {
        obj[field.name] = field.value; // 通过变量，将属性值，属性一起放到对象中
    })
	$.ajax({
	async: async,
    url: url,
    type: 'POST',
    data: JSON.stringify(obj),
    contentType:"application/json",
    headers	:{'Content-Type': 'application/json;charset=UTF-8'},
    dataType :"json",
    success : success,
	error : function(data) {
		if(data.isTan !='' && data.isTan != null){
			tankuang(data.isTan);
		}else{
			tankuang("操作失败");
		}
	},
  });
  }
