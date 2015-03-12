package com.geebay.wxsq.account.control.weixin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geebay.wxsq.account.service.weixin.WeixinReplyServiceImp;

@Controller
@RequestMapping(value="account")
public class WeixinReplyController {
	
	@Autowired
	private WeixinReplyServiceImp weixinReplyServiceImp;
	
	@RequestMapping(value="weixin/reply/subscribe/settings",method=RequestMethod.GET)
	public String subscribe(Model  model,HttpRequest request){
				
		return "account/weixin/reply/subscribe";
		
	}
	
	@RequestMapping(value="weixin/reply/subscribe/settings",method=RequestMethod.POST)
	public String subscribe(){
		return null;
		
	}
	
	
	
	@RequestMapping(value="weixin/reply/home",method=RequestMethod.GET)
	public String diy(Model model,HttpServletRequest request){
		
		return "account/weixin/reply/home";
		
	}
	
	
	@RequestMapping(value="weixin/reply/text/create",method=RequestMethod.GET)
	public String replyTextGet(){
		
		return "account/weixin/reply/text";
		
	}
	
	@RequestMapping(value="weixin/reply/text/create",method=RequestMethod.POST)
	@ResponseBody
	public String replyTextPost(){
		
		return "account/weixin/reply/text";
		
	}	

}
