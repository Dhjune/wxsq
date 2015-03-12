package com.geebay.wxsq.account.control.weixin;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geebay.wxsq.account.inteceptor.Permission;
import com.geebay.wxsq.account.service.weixin.WxAccountServiceImp;
import com.geebay.wxsq.common.intecepter.FormToken;
import com.geebay.wxsq.common.mongo.Expression;
import com.geebay.wxsq.common.mongo.MongoAbstract;
import com.geebay.wxsq.common.mongo.MongoResolver;
import com.geebay.wxsq.common.mongo.PageNav;
import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.account.base.WxAccount;
import com.geebay.wxsq.model.account.base.WxsqUser;

/** 
* @ClassName: WxAccountController 
* @Description: 当前社区用户对于微信公众号的操作，公众号实例创建、修改、删除、查看的基本操作.
* @author june
* @date 2015年3月9日 上午11:26:23 
*  
*/
@Controller
@RequestMapping("/account")
public class WxAccountController {
	
	

	@Autowired
	private WxAccountServiceImp wxAccountServiceImp;
	
	@Autowired
	private  MongoResolver mongoResolver;
	
	/** 
	* @Title: getWxAccountListByGet 
	* @Description: 
	* @param @param request
	* @param @param model
	* @param @return   
	* @return String   
	* @throws 
	*/
	@RequestMapping(value={"weixin/list"},method=RequestMethod.GET)
	public String getWxAccountListByGet(HttpServletRequest request,Model model){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		WxAccount wxaccount =  new  WxAccount();			
		int pageIndex = 1;						
		String url = "/account/weixin/list%s";
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
	
	
	
	
	
	/** 
	* @Title: getWxAccountListByPost 
	* @Description: TODO
	* @param expressions
	* @param request
	* @param model
	* @return String
	* @throws 
	*/
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
		String url = "/account/weixin/list%s";
		
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
	public String wxAccountCreate_post(HttpServletRequest request,HttpSession session,Model model,com.geebay.wxsq.model.account.base.WxAccount wxaccount){
		wxaccount.setCreateTime(new Date());
		WxsqUser user =  (WxsqUser) session.getAttribute("wxsqUser");
		if(user!=null){
			wxaccount.setWxsqUserId(user.getId());
			boolean  success = wxAccountServiceImp.save(wxaccount);
			if(success){
				session.setAttribute("wxAccount", wxaccount);
				return  "redirect:/account/weixin/list";
			}else{
				model.addAttribute("wxaccount", wxaccount);	
				return "account/weixin/create";
			}
		}else{
			return  "redirect:/list";
		}
		
		
	}
	
	@RequestMapping(value="weixin/openuse")
	@ResponseBody
	public Map openUse(HttpServletRequest request,HttpSession session){
		String  wxAccountId  =   request.getParameter("wxAccountId");
		WxAccount wxAccount = null;
		Map map =  new HashMap();
		if(wxAccountId !=null && !wxAccountId.equals("")){
			
			wxAccount =  mongoResolver.findOne(wxAccountId, WxAccount.class);
			if(wxAccount!= null){
				map.put("status", 1);
				map.put("success","可以对当前微信公众号："+wxAccount.getWeixinName()+"进行设置");
				map.put("wxAccount", wxAccount);
				session.setAttribute("wxAccount", wxAccount);
				
			}
			
		}else{
			map.put("status", 0);
			map.put("error", "该账号无法启用，或发生未知错误");
		}
	
		return map;
	}
	
	
	
	
	
	
}
