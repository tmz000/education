var body=document.body;
var realName=null;
//var token = window.location.search
var token = localStorage.getItem("token");
function getMsUser(){
	$.ajax({
		type:'POST',
		url:'/msUser/getMsUser?token='+token,
		contentType:'application/json',
		data:JSON.stringify({sysId:token}),
		success:function(res){
			if(res.status == '01'){
				realName=res.rows.realName;
			}else{
				alert("登录超时，请重新登录！")
				window.open("/",'_top')
			}
		}
	})
}
$(function(){
	getMsUser();
})
/*body.onclick=function(){
	getMsUser();
}*/
$('a').click(function(){
	if(realName == "" || realName==null){
		$(this).each(function(){
			if($(this).html().indexOf("退出系统") < 0){
				if($(this).html().indexOf("优智教育") < 0){
					if($(this).html().indexOf("个人资料") < 0){
						alert("请在【优智教育-个人资料】里录入该账号真实姓名！")
						window.location.reload()
					} 
				}
			}
		})
	}
})

$("#queryTable").click(function(){
	init();
})
$("#clearTable").click(function(){
	$("#toolbar input").each(function(){
		 $(this).val('');
	})
	$("#toolbar select").each(function(){
		 $(this).val('');
	})
	init();
})
$(".rightCss").append('<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>')
$(".form_datetime").datetimepicker({
    language: "zh-CN",
    format: "yyyy-mm-dd",
    autoclose: true,
    minView: "month"
});
$(".glyphicon-remove").click(function(){
    $($($(this).parent()).prev()).val("");
})


String.prototype.startWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substr(0,str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  
String.prototype.endWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substring(this.length-str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  
