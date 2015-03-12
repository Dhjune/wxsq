

function  Ws(){
	this.alive =  true;
	if(window.XMLHttpRequest){
		this.req = new XMLHttpRequest();
	}else{
		this.req = new ActiveXObject("Msxml2.XMLHTTP");  //ie
	}
}

var ws =  new Ws();
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
	
Ws.prototype.get = function(target,_blank){
	
	var $this = $(target),_clickTab = $this.attr('link'); 
	$.get(_clickTab,function (data ,status){
		if(_blank!=""){
			$(_blank).html(data); 
		}else{
			$("#container_iframe").html(data); 
		}
	});
}

Ws.prototype.post = function(target){
	
	var $this = $(target),_clickTab = $this.attr('link'); 
	$.post(_clickTab,function (data ,status){
		$("#container_iframe").html(data); 
	});
}

Ws.prototype.postForm =  function (target,_blank){
	
	var  form = $(target);
	
	 	jQuery.ajax({
		    url: form.attr("action"),
		    data:form.serialize(),
		    type:"POST",  
		    success:function(data)
		    {
		        $(_blank).html(data); 
		    }
		    });
		    return false;
	
}

Ws.prototype.navpost = function(target,_blank){
	
	var $this = $(target),_clickTab = $this.attr('link'); 
	console.log($.toJSON(AjaxArr));
	$.ajax({  
        url : _clickTab,  
        type : 'POST',  
        data : $.toJSON(AjaxArr),  
         
        contentType : 'application/json',  
        success : function(data, status, xhr) {  
        	if(_blank!=""){
        		$("#"+_blank).html(data); 
        	}else{
        		$("#container_iframe").html(data); 
        	}
        }
	});
}

Ws.prototype.expressval = function (target){
	
	var $target =  $(target);
	var field_name = $target.attr("name");
	var operation_id = $target.attr("operation_id");	
	var field_value = $target.val();	
	var operation = $target.attr("operation");
	var operation_opId = $target.attr("operation_opId");
	
	for(var i = 0;i<AjaxArr.length;i++){		
		var  obj = AjaxArr[i];	
		if(field_name == obj.name){
			
			obj[operation_opId] = operation;	
			obj[operation_id] =  field_value;
			return ;
		}			
	}	
	var  obj = {};
	obj["name"]= field_name;
	
	obj[operation_opId] = operation;
	obj[operation_id]= field_value;
	AjaxArr.push(obj);
	
} 

Ws.prototype.selectval = function (target){
	
	var $target =  $(target);
	var field_name = $target.attr("name");
	var operation_id = $target.attr("operation_id");
	var operation = $target.attr("operation");
	var operation_opId = $target.attr("operation_opId");
	var field_value =  $target.val();	
	for(var i = 0;i<AjaxArr.length;i++){		
		var  obj = AjaxArr[i];	
		if(field_name == obj.name){
			obj[operation_opId] = operation;
			obj[operation_id] =  field_value;
			return ;
		}			
	}	
	var  obj = {};
	obj["name"]= field_name;
	
	obj[operation_opId] = operation;
	obj[operation_id]= field_value;
	AjaxArr.push(obj);
	
} 
  
function admin_login(){
		
		var adata={};
		adata['userName']=$('#userName').val();
		adata['password'] = $('#password').val();
		console.log($('#userName').val());
		$.ajax({  
	        url : "/user/login",  
	        type : 'POST',  
	        data : $.toJSON(adata),  
	        dataType : 'json',  
	        contentType : 'application/json',  
	        success : function(data, status, xhr) {  
	        	var html = "";
	        	var message="";
 				if(data!=null && data.success){
 					html+="<li><a onclick=\"openAccount('"+data.wxsqUser.id+"')\" href='/account/index?wxsqUserId="+data.wxsqUser.id+"'> <span class='glyphicon glyphicon-user' aria-hidden='true'>"+data.wxsqUser.userName+"</span></a></li>";
 					
 					html +="<li><a href='#' onclick=\"admin_logout('"+data.wxsqUser.id+"')\">退出</a></li>"	
 				  
			
 					
 					message += "<div class='alert alert-success fade in'>";
 					message += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
 					message += "<strong>"+data.message+"</strong></div>";
					
					$('#admin_view').html(html);
					$('#alert_view').html(message);
					
 				}else{
 					
 					message += "<div class='alert alert-danger fade in'>";
 					message += "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
 					message += "<strong>"+data.message+"</strong></div>";					
					$('#alert_view').html(message);
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
	        url : "/user/logout",  
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
	        url : "/user/permission",  
	        type : 'POST',  
	        data : $.toJSON(adata),  
	        dataType : 'json',  
	        contentType : 'application/json',  
	        success : function(data, status, xhr) {  
	        	var html = "";
	        	var alert="";
 				if(data!=null && data.success){
 					
 					window.location.href = "/account/index?wxsqUserId="+admin_id;
				    
					
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

