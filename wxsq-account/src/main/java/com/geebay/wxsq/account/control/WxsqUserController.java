package com.geebay.wxsq.account.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geebay.wxsq.account.service.WxsqUserServiceImp;
import com.geebay.wxsq.model.account.base.WxsqUser;

@Controller
public class WxsqUserController {
	
	@Autowired
	private WxsqUserServiceImp wxsqUserServiceImp;
	
	@RequestMapping(value="user/register",method=RequestMethod.GET)
	public String register_get(){
		
		
		return "account/user/register";
		
	}
	
	@RequestMapping(value="user/register",method=RequestMethod.POST)
	public String register_post(WxsqUser wxsqUser,Model model,HttpServletResponse resp,HttpSession session) throws UnsupportedEncodingException, IOException{
		
		wxsqUser.setCreateTime(new Date());
		wxsqUser.setLevel(2);
		wxsqUser.setStatus(1);		
		if(wxsqUserServiceImp.register(wxsqUser)){
			session.setAttribute("wxsqUser", wxsqUser);				
			return "redirect:/index";
		}else{
			model.addAttribute("wxsqUser", wxsqUser);
			model.addAttribute("error", "用户已经被注册");
			return "account/user/register";
		}
		
		
		
		
	}
	
	@RequestMapping(value="user/login",method=RequestMethod.POST)
	@ResponseBody
	public Map login_post(@RequestBody Map map,HttpServletRequest request,Model model,HttpSession session){
		
		String userName = (String) map.get("userName");
		String password = (String) map.get("password");
		
		Map rmap = wxsqUserServiceImp.login(userName,password);
	
		if((Boolean) rmap.get("success")){
		
			session.setAttribute("wxsqUser", (WxsqUser)rmap.get("wxsqUser"));
		
		}
		return  rmap;
				
	}
	
	@RequestMapping(value="user/logout",method=RequestMethod.POST)
	@ResponseBody
	public Map logout_post(@RequestBody Map map,HttpServletRequest request,Model model,HttpSession session) throws Exception{
		
		
		return  wxsqUserServiceImp.logout(map,session);
				
	}
	
	
	@RequestMapping(value="user/permission",method=RequestMethod.POST)
	@ResponseBody
	public Map permission(@RequestBody Map map,HttpServletRequest request,Model model,HttpSession session) throws Exception{
		Map rmap =  new HashMap();
		String wxsqUserId = (String) map.get("wxsqUserId");
		WxsqUser wxsqUser = (WxsqUser) session .getAttribute("wxsqUser");
		if(wxsqUser!=null && wxsqUser.getId().equals(wxsqUserId)){
			rmap.put("success", true);
		}else{
			rmap.put("success", false);
			rmap.put("errmsg", "用户已经退出，或者权限不足");
		}
		
		return rmap;
		
				
	}
	
}
