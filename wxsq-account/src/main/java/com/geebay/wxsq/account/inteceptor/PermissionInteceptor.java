package com.geebay.wxsq.account.inteceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.geebay.wxsq.model.account.base.WxsqUser;


public class PermissionInteceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;		 		 
        Method method = handlerMethod.getMethod();                  
        Permission annotation = method.getAnnotation(Permission.class);
        if (annotation != null) {
        	if(matchRole(annotation,request)){
        		return true;
        	}else{       		
        		response.sendRedirect("/wxsq/account/error/permission");
        		return false;       		
        	}        	
        }
		return true;		
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}
	
	public boolean matchRole(Permission annotation,HttpServletRequest request){
		HttpSession session = request.getSession();		
		WxsqUser wxsqUser =  (WxsqUser) session.getAttribute("wxsqUser");
		if(wxsqUser!=null){
			return true;
		}else{
			return false;
		}
	}

}
