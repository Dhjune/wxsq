package com.geebay.wxsq.account.control.weixin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geebay.wxsq.account.service.weixin.WxAccountServiceImp;

@Controller
@RequestMapping("/account")
public class WxAccountController {

	@Autowired
	private WxAccountServiceImp wxAccountServiceImp;
	
	@RequestMapping(value={"wxaccount/list"})
	public String WxAccount(HttpServletRequest request,Model model){
						
		return "account/weixin/list";	
	}
	
	@RequestMapping(value="wxaccount/create",method=RequestMethod.GET)
	public String wxAccountCreate_get(){
		
		return "account/wxaccount/create";
	}
	
	@RequestMapping(value="wxaccount/create",method=RequestMethod.POST)
	public String wxAccountCreate_post(){
		return "account/wxaccount/create";
	}
	
	
}
