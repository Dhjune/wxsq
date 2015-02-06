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

