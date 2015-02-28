package com.geebay.wxsq.account.control.weixin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.geebay.wxsq.common.mongo.PageNav;
import com.geebay.wxsq.model.Constants;
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
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		WxAccount wxaccount =  new  WxAccount();			
		int pageIndex = 1;						
		String url = "list%s";
		Expression[]  expressions = null;
		List<Expression> list = null;
		if(expressions  != null){
			list =  Arrays.asList(expressions);
		}		
		PageNav<WxAccount> context =null;
		try {
			context = mongoResolver.PageNavlist(list, wxaccount, true, pageIndex,Constants.ACCOUNT_DEFAULT_PAGE_SIZE , url);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("context", context);
		
		return "account/weixin/list";	
	}
	

	@RequestMapping(value="wxaccount/create",method=RequestMethod.GET)
	public String wxAccountCreate_get(){
		
		return "account/wxaccount/create";
	}

	@RequestMapping(value={"weixin/list"},method=RequestMethod.POST)
	public String getWxAccountListByPost(@RequestBody Expression[]  expressions,HttpServletRequest request,Model model){
		
		WxAccount wxaccount =  new  WxAccount();
		String pageIndexString =  request.getParameter("pageIndex");		
		
		int pageIndex = 1;
		
		if(pageIndexString !=null && !pageIndexString.equals("") ){	
			String s = "\\d+";
			Pattern  pattern=Pattern.compile(s); 
			Matcher mt=pattern.matcher(pageIndexString); 
			if(mt.find()){
				pageIndex = Integer.parseInt(pageIndexString);
			}
			
			
		}else{	
			
			pageIndex = 1;	
			
		}		
		String url = "list%s";
		
		List<Expression> list =  Arrays.asList(expressions);
		PageNav<WxAccount> context =null;
		try {
			context = mongoResolver.PageNavlist(list, wxaccount, true, pageIndex,Constants.ACCOUNT_DEFAULT_PAGE_SIZE , url);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("context", context);
		
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
	public String wxAccountCreate_post(HttpServletRequest request,Model model,com.geebay.wxsq.model.account.base.WxAccount wxaccount){
		wxaccount.setCreateTime(new Date());
		boolean  success = wxAccountServiceImp.save(wxaccount);
		if(success){
			return  "redirect:/account/weixin/list";
		}else{
			model.addAttribute("wxaccount", wxaccount);	
			return "account/weixin/create";
		}
		
	}
	
	
}
