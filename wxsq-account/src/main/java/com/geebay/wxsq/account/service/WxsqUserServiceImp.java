package com.geebay.wxsq.account.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.account.dao.WxsqUserDaoImp;
import com.geebay.wxsq.common.api.service.WxsqUserService;
import com.geebay.wxsq.model.account.base.WxsqUser;

@Service(value="account_wxsqUserServiceImp")
public class WxsqUserServiceImp implements WxsqUserService{
	
	@Autowired
	private WxsqUserDaoImp wxsqUserDaoImp;
	
	public void save(WxsqUser wxsqUser){
		
		wxsqUserDaoImp.save(wxsqUser);
		
	}
	
	public boolean registered (String userName){
		return wxsqUserDaoImp.exist(userName);
	}
	
	public boolean register(WxsqUser wxsqUser){
		
		if(registered(wxsqUser.getUserName())){
			return false;
		}
		save(wxsqUser);
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public Map login(String userName, String password) {
		
		return wxsqUserDaoImp.login(userName, password);
		
	}
	
	
	public Map logout(Map map,HttpSession session) throws Exception{
		Map rMap = new HashMap();		
		WxsqUser user =  (WxsqUser) session.getAttribute("wxsqUser");
		System.out.println(session.getAttribute("wxsqUser"));
		if(user!=null){
			System.out.println("agalsgjl");
			if(map.get("wxsqUserId").toString().equals(user.getId())){
				session.removeAttribute("wxsqUser");
				rMap.put("message", "登出成功，如要进行操作，请重新登录");
				rMap.put("success", true);
			}else{
				rMap.put("message", "登出失败，请稍后重试");
				rMap.put("success", false);
			}
		}else{
			rMap.put("message", "用户已经登出，请勿重复操作");
			rMap.put("success", false);
		}
		return rMap;
	}
	

}
