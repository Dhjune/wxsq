package com.geebay.wxsq.account.control.weixin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geebay.wxsq.account.inteceptor.Permission;
import com.geebay.wxsq.account.service.weixin.WxAccountServiceImp;
import com.geebay.wxsq.common.intecepter.FormToken;
import com.geebay.wxsq.common.mongo.Expression;
import com.geebay.wxsq.common.mongo.MongoAbstract;
import com.geebay.wxsq.common.mongo.MongoResolver;
import com.geebay.wxsq.model.account.base.WxAccount;

@Controller
@RequestMapping("/account")
public class WxAccountController {
	
	

	@Autowired
	private WxAccountServiceImp wxAccountServiceImp;
	
	@Autowired
	private  MongoResolver mongoResolver;
	
	@RequestMapping(value={"weixin/list"},method=RequestMethod.GET)
	public String getWxAccountListByGet(HttpServletRequest request,Model model){
		
		String wxsqUserId = "";
		String lastKey = "";
		wxAccountServiceImp.list(wxsqUserId,lastKey);
		
		return "account/weixin/list";	
	}
	
	@RequestMapping(value={"weixin/list"},method=RequestMethod.POST)
	public String getWxAccountListByPost(HttpServletRequest request,Model model){
		
		WxAccount wxaccount =  new  WxAccount();		
		List<Expression> list =  null;
		List<WxAccount> result =  mongoResolver.list(list, wxaccount);
		
		return "account/weixin/list";	
	}
	
	
	
	@RequestMapping(value="weixin/create",method=RequestMethod.GET)
	@FormToken(SaveToken=true)
	@Permission()
	public String wxAccountCreate_get(Model model,HttpServletRequest request){
		
		
		return "account/weixin/create";
		
	}
	
	@RequestMapping(value="weixin/create",method=RequestMethod.POST)
	@FormToken(RemoveToken=true)
	@Permission()
	public String wxAccountCreate_post(HttpServletRequest request,com.geebay.wxsq.model.account.base.WxAccount wxaccount){
		
		boolean  success = wxAccountServiceImp.save(wxaccount); 
		if(success){
			return  "redirect:/account/weixin/list";
		}else{
				
			return "account/weixin/create";
		}
		
	}
	
	
}
