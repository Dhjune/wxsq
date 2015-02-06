package com.geebay.wxsq.account.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geebay.wxsq.model.account.base.WxsqUser;

@Controller
public class AccountController {

	@RequestMapping("/account/index")
	public String accout_index(HttpServletRequest request,HttpSession session,Model model){
		
		String  wxsqUserId =  request.getParameter("wxsqUserId");
		WxsqUser wxsqUser =  (WxsqUser) session.getAttribute("wxsqUser");
		System.out.println(wxsqUser);
		System.out.println(wxsqUserId);
		if(wxsqUser!=null && wxsqUser.getId().equals(wxsqUserId)){
			
			return "account/index";	
		}
		else{
			
			return "redirect:/index";
		}
		
		
		
	}
	@RequestMapping("/index")
	public String wxsq_index(){
				
		return "index";
		
	}
	
	@RequestMapping(value="/account/error/permission")
    public String permission(){
		
    	return "account/error/permission";
    	
    }
	
}
