package com.geebay.wxsq.account.control.weixin;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geebay.wxsq.account.inteceptor.Permission;
import com.geebay.wxsq.account.service.weixin.WeixinReplyServiceImp;
import com.geebay.wxsq.common.intecepter.FormToken;
import com.geebay.wxsq.common.mongo.Expression;
import com.geebay.wxsq.common.mongo.MongoResolver;
import com.geebay.wxsq.common.mongo.Operate;
import com.geebay.wxsq.common.mongo.PageNav;
import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.account.base.WxAccount;
import com.geebay.wxsq.model.account.base.WxMessage;


/** 
* @ClassName: WxMessageController 
* @Description: TODO
* @author june
* @date 2015年3月9日 上午11:23:02 
*  
*/
@Controller
@RequestMapping(value="account")
public class WxMessageController {
	
	@Autowired
	private MongoResolver mongoResolver;
	
	@Autowired
	private WeixinReplyServiceImp weixinReplyServiceImp;
	
	
	@RequestMapping(value="weixin/message/home")
	public String messageHome(Model model){
		
		WxMessage 	wxMessage = new WxMessage();	 	
		int pageIndex = 1;						
		String url = "/account/weixin/message/text/list%s";
		Expression[]  expressions = new Expression[2];
		Expression express = new Expression();
		express.name = "type";
		express.initial_op = Operate.IS;
		express.initial_v = "text";
	
		expressions[0] =  express;
		
		List<Expression> list = null;
		if(expressions  != null){
			list =  Arrays.asList(expressions);
		}		
		PageNav<WxMessage> context =null;
		try {
			context = mongoResolver.PageNavlist(list, wxMessage, true, pageIndex,Constants.ACCOUNT_DEFAULT_PAGE_SIZE , url);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		
		
		model.addAttribute("context", context);
		
		
		return "account/weixin/message/home";
		
	}
	
	
	
	
	/** 
	* @Title: textMessageList 
	* @Description: 获取文本消息列表 
	* @param request
	* @return   String
	* @throws 
	*/
	@RequestMapping(value="weixin/message/text/list",method=RequestMethod.GET)
	public String textMessageList(HttpServletRequest request,Model model){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		WxMessage 	wxMessage = new WxMessage();	 	
		int pageIndex = 1;						
		String url = "/account/weixin/message/text/list%s";
		Expression[]  expressions = null;
		List<Expression> list = null;
		if(expressions  != null){
			list =  Arrays.asList(expressions);
		}		
		PageNav<WxMessage> context =null;
		try {
			context = mongoResolver.PageNavlist(list, wxMessage, true, pageIndex,Constants.ACCOUNT_DEFAULT_PAGE_SIZE , url);
			
			context.setView("message_view");
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
				
		model.addAttribute("context", context);
		
		return "account/weixin/message/text/list";
		
	}
	
	
	@RequestMapping(value="weixin/message/text/list",method=RequestMethod.POST)
	public String textMessageList(@RequestBody Expression[]  expressions,HttpServletRequest request,Model model){
		
		WxMessage 	wxMessage = new WxMessage();	 	
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
		String url = "/account/weixin/message/text/list%s";
		
		List<Expression> list =  Arrays.asList(expressions);
		PageNav<WxMessage> context =null;
		try {
			context = mongoResolver.PageNavlist(list, wxMessage, true, pageIndex,Constants.ACCOUNT_DEFAULT_PAGE_SIZE , url);
			context.setView("message_view");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("context", context);
		return "account/weixin/message/text/list";
		
	}
	
	@RequestMapping(value="weixin/message/text/create",method=RequestMethod.GET)
	@FormToken(SaveToken=true)
	@Permission()
	public String createTextMessage(Model model){
		
		return  "account/weixin/message/text/create";
		
	}
	
	
	@RequestMapping(value="weixin/message/text/create",method=RequestMethod.POST)
	@FormToken(RemoveToken=true)
	@Permission()
	public String createTextMessage(WxMessage wxMessage,HttpServletRequest request,Model model){	
			if(wxMessage!=null){
				wxMessage.setType("text");
				if(weixinReplyServiceImp.save(wxMessage)){
					
					return  "redirect:/account/weixin/message/text/list";
				}else{
					model.addAttribute("wxMessage", wxMessage);	
					return "account/weixin/message/text/create";	
				}	
				
			}else{
				model.addAttribute("wxMessage", wxMessage);	
				return "account/weixin/message/text/create";
			}
			
	}
	
	
	
	
	
	@RequestMapping(value="weixin/message/news/list",method=RequestMethod.GET)
	public String NewsMessageList(HttpServletRequest request){
		
		return "account/weixin/message/news/list";
		
	}
	
	@RequestMapping(value="weixin/message/news/list",method=RequestMethod.POST)
	public String NewsMessageList(){
		
		return "account/weixin/message/news/list";
		
	}
	
	@RequestMapping(value="weixin/message/news/create",method=RequestMethod.GET)
	public String createNewsMessage(Model model){
		
		return  "account/weixin/message/news/create";
		
	}
	
	
	@RequestMapping(value="weixin/message/news/create",method=RequestMethod.POST)
	public String createNewsMessage(){
		
		return "";
		
	}




	public WeixinReplyServiceImp getWeixinReplyServiceImp() {
		return weixinReplyServiceImp;
	}




	public void setWeixinReplyServiceImp(WeixinReplyServiceImp weixinReplyServiceImp) {
		this.weixinReplyServiceImp = weixinReplyServiceImp;
	}
	
	
	
}
