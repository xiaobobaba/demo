var login={

	baseUrl : "http://xiaobo.nat300.top/",
	addYZ: function(){
		if($("#userName").val() ==''){
			tankuang('姓名不能为空');
			return ;
		}
		if($("#email").val() ==''){
			tankuang('邮箱不能为空');
			return ;
		}
		if($("#passWord").val() ==''){
			tankuang('密码不能为空');
			return ;
		}
		if($("#passWord2").val() != $("#passWord").val()){
			tankuang('两次密码不一样');
			return ;
		}
		if($("#passWord2").val() ==''){
			tankuang('密码不能为空');
			return ;
		}
		if($("#birthDay").val() ==''){
			tankuang('生日不能为空');
			return ;
		}
		if($("#zwjs").val() ==''){
			tankuang('自我介绍不能为空');
			return ;
		}
		$("#emailUser").val($("#email").val());
		var params = $('#add_form').serializeArray();
		ajax(login.baseUrl+'login/wxYanZheng',params,
	        	function(data) {
		        	isTK(data);
		        	if(data.success){
		        		layer.prompt({
		      			  formType: 2,
		      			  value: '请输入邮箱收到的验证码',
		      			  title: '邮箱验证',
		      			  area: ['250px', '30px'] //自定义文本域宽高
		      			}, function(value, index, elem){
		      				if(value ==null || value ==''){
		      					return;
		      				}
		      				console.log(value);
		      				$("#yzm").val(value);
		      				var params = $('#add_form').serializeArray();
		      				ajax(login.baseUrl+'login/userAdd',params,
		      			        	function(data) {
		      							isTK(data);
		      							if(data.success){
		      								window.location.href="http://xiaobo.nat300.top/xiaobo/index.html";
		      							}
		      				        	
		      				     })
		      			  layer.close(index);
		      			});
		        	}
		     	});
         //JSON.stringify(obj)
        /*ajax(login.baseUrl+'login/userAdd',params,
        	function(data) {
	        	isTK(data);
	        	$("#chishenme").click();
	     	});*/
	},
	
	login : function(){
		if($("#email_login").val() ==''){
			tankuang('邮箱不能为空');
			return ;
		}
		if($("#passWord_login").val() ==''){
			tankuang("密码不能为空！");
			return ;
		}
		 var params = $('#login_form').serializeArray();
         ajax(login.baseUrl+'login/login',params,
         	function(data) {
        	 isTK(data);
        	 if(data.success){
        		 window.location.href="http://xiaobo.nat300.top/xiaobo/index.html";
				}
         });
	},
	
	
	//点击调用文件上传按钮
	img_import : function() {
		$("#file-input").click();
	},
	zhuce : function() {
		$("#zhuce").click();
	},
	
};
$(function() {
	//图片上传
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
	        	$("#touXiang").val("/"+data.obj);
	        	$("#imgName").text(da);
	        },
	    });
	    //$('#img_import').attr('file:///C:/Users/xiaobo/Desktop','3.jpg');
	    //document.getElementById("img_import").src = s;
	});
	
	//点击图片打开文件上传
	$("#img_import").click(login.img_import);
	$("#birthDay").val(dateShow('yyyy-MM-dd',new Date));
	//$("#isPassword_login").click();
	$(document).on('click','.checkbox',function () {
		tankuang("记住密码")
	 });
	//
	$("#sex").change(function(){
		if($("#sex").val()=="1"){
			$("#nv").html('<i class="layui-icon">&#xe662;</i>&nbsp&nbsp');
		}else{
			$("#nv").html('<i class="layui-icon">&#xe661;</i>&nbsp&nbsp');
		}
		
	});
});