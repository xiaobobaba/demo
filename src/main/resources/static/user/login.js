var login={

	baseUrl : "http://xiaobo.nat300.top/",
	addYZ: function(){
		if($("#userName").val() ==''){
			alert('姓名不能为空');
			return ;
		}
		if($("#email").val() ==''){
			alert('邮箱不能为空');
			return ;
		}
		if($("#passWord").val() ==''){
			alert('密码不能为空');
			return ;
		}
		if($("#birthDay").val() ==''){
			alert('生日不能为空');
			return ;
		}
		if($("#zwjs").val() ==''){
			alert('自我介绍不能为空');
			return ;
		}
		var params = $('#login_form').serializeArray();
		 var obj = {}; //声明一个对象
		 $.each(params, function(index, field) {
             obj[field.name] = field.value; //通过变量，将属性值，属性一起放到对象中
         })
         //JSON.stringify(obj)
		$.ajax({
	        url: login.baseUrl+'login/userAdd',
	        type: 'POST',
	        data: JSON.stringify(obj),
	        contentType:"application/json",
	        headers	:{'Content-Type': 'application/json;charset=UTF-8'},
	        dataType :"json",
	        success : function(data) {
				if (data.result) { //登录成功
					console.log("success");
					location.href = "<%=basePath%>GB-canvas-turntable.html";  //跳转
				} else { //登录失败
					$("#errorFont").html("用户名或密码错误!");
				}
			},
			error : function(data) {
			},
	      });
	},
	img_import : function() {
		$("#file-input").click();
	},
	
};
$(function() {
	$("#file-input").on("change", function() {
		//获取文件对象，files是文件选取控件的属性，存储的是文件选取控件选取的文件对象，类型是一个数组
	    var fileObj = this.files[0];
	    var name = this.files[0].name;
	    var da = dateShow('yyyy-MM-dd hh:mm:ss',this.files[0].lastModifiedDate);
	    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
	    var formData = new FormData();
	    formData.append('file', fileObj);
	    $.ajax({
	        url: login.baseUrl+'login/upload',
	        type: "post",
	        dataType: "json",
	        data: formData,
	        async: false,
	        cache: false,
	        contentType: false,
	        processData: false,
	        success: function (data) {
	        	document.getElementById("img_import").src ="/"+data.obj
	        	$("#imgName").text(da);
	        },
	    });
	    //$('#img_import').attr('file:///C:/Users/xiaobo/Desktop','3.jpg');
	    //document.getElementById("img_import").src = s;
	});
	
	//点击图片打开文件上传
	$("#img_import").click(login.img_import);
	$("#birthDay").val(dateShow('yyyy-MM-dd',new Date));
	// $(".button-success").click(login.addYZ);
	/*$(document).on('click','.button-success',function () {
	   	 $.toast("操作失败");
	    });*/
});