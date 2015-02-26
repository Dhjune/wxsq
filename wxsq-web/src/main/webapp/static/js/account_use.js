

function  Ws(){
	this.alive =  true;
	if(window.XMLHttpRequest){
		this.req = new XMLHttpRequest();
	}else{
		this.req = new ActiveXObject("Msxml2.XMLHTTP");  //ie
	}
}

var $Ws =  new Ws();
var AjaxArr =  new Array();
Ws.prototype.ajax = function (url,type,data,content_type){
	
	this.req.open(type,url,true);
	this.req.setRequestHeader("Content-Type", content_type);
	this.req.send($.toJSON(data));
	this.req.onreadystatechange=function(){
		if (req.readyState==4 && req.status==200){			
			return eval("("+req.responseText+")");
		}
	};
	
}	

Ws.prototype.get = function(target){
	
	var $this = $(target),_clickTab = $this.attr('link'); 
	$.get(_clickTab,function (data ,status){
		$("#container_iframe").html(data); 
	});
}

Ws.prototype.expressval = function (target){
	
	var $target =  $(target);
	var field_name = $target.attr("name");
	var field_id = $target.attr("id");	
	var field_value = $target.attr("value");	
	for(var i = 0;i<AjaxArr.length;i++){		
		var  obj = AjaxArr[i];	
		if(field_name == obj.name ){
			obj[field_id] = field_id;
			obj[field_value] =  field_value;
			return ;
		}			
	}	
	var  obj = {};
	obj[field_name]= field_name;
	obj[field_id]= field_id;
	obj[field_value]= field_value;
	AjaxArr.push(obj);
	
} 
  
function admin_login(){
		
		var adata={};
		adata['userName']=$('#userName').val();
		adata['password'] = $('#password').val();
		console.log($('#userName').val());
		$.ajax({  
	        url : "/wxsq/user/login",  
	        type : 'POST',  
	        data : $.toJSON(adata),  
	        dataType : 'json',  
	        contentType : 'application/json',  
	        success : function(data, status, xhr) {  
	        	var html = "";
	        	var alert="";
 				if(data!=null && data.success){
 					html+="<li><a onclick=\"openAccount('"+data.wxsqUser.id+"')\" href='/wxsq/account/index?wxsqUserId="+data.wxsqUser.id+"'> <span class='glyphicon glyphicon-user' aria-hidden='true'>"+data.wxsqUser.userName+"</span></a></li>";
 					
 					html +="<li><a href='#' onclick=\"admin_logout('"+data.wxsqUser.id+"')\">退出</a></li>"	
 				  
					
					alert += "<div class='alert alert-success fade in'>";
					alert += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
					alert += "<strong>"+data.message+"</strong></div>";
					
					$('#admin_view').html(html);
					$('#alert_view').html(alert);
					
 				}else{
 					
 					alert += "<div class='alert alert-danger fade in'>";
					alert += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
					alert += "<strong>"+data.message+"</strong></div>";					
					$('#alert_view').html(alert);
 				}
 				
	        	
	        },  
	        Error : function(xhr, error, exception) {  
	            
	            alert(exception.toString());  
	        }  
	    }); 
		
	}	
	
	function admin_logout(admin_id){
		adata={"wxsqUserId":admin_id};
		$.ajax({  
	        url : "/wxsq/user/logout",  
	        type : 'POST',  
	        data : $.toJSON(adata),  
	        dataType : 'json',  
	        contentType : 'application/json',  
	        success : function(data, status, xhr) {  
	        	var html = "";
	        	var alert="";
 				if(data!=null && data.success){
 					
 					
				    html +="<li ><a href=''#' data-toggle='modal' data-target='#AdminModal'>登录</a></li>";
				    html += "<li><a href='/wxsq/user/register' >注册</a></li>";
					alert += "<div class='alert alert-success fade in'>";
					alert += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
					alert += "<strong>"+data.message+"</strong></div>";
					
					$('#admin_view').html(html);
					$('#alert_view').html(alert);
					
 				}else{
 					
 					alert += "<div class='alert alert-danger fade in'>";
					alert += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
					alert += "<strong>"+data.message+"</strong></div>";					
					$('#alert_view').html(alert);
 				}
 				
	        	
	        },  
	        Error : function(xhr, error, exception) {  
	            
	            alert(exception.toString());  
	        }  
	    }); 
	}
	
	function  openAccount(admin_id){
		adata={"wxsqUserId":admin_id};
		$.ajax({  
	        url : "/wxsq/user/permission",  
	        type : 'POST',  
	        data : $.toJSON(adata),  
	        dataType : 'json',  
	        contentType : 'application/json',  
	        success : function(data, status, xhr) {  
	        	var html = "";
	        	var alert="";
 				if(data!=null && data.success){
 					
 					window.location.href = "/wxsq/account/index?wxsqUserId="+admin_id;
				    
					
 				}else{
 					
 					alert += "<div class='alert alert-danger fade in'>";
					alert += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
					alert += "<strong>"+data.errmsg+"</strong></div>";					
					$('#alert_view').html(alert);
 				}
 				
	        	
	        },  
	        Error : function(xhr, error, exception) {  
	            
	            alert(exception.toString());  
	        }  
	    }); 
	}

