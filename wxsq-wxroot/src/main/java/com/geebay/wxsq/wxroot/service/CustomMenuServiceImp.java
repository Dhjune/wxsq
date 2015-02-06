package com.geebay.wxsq.wxroot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.common.api.service.CustomMenuService;
import com.geebay.wxsq.model.account.weixin.CustomMenu;
import com.geebay.wxsq.wxroot.dao.CustomMenuDaoImp;

@Service
public class CustomMenuServiceImp  implements CustomMenuService{
	
	@Autowired
	private  CustomMenuDaoImp customMenuDaoImp;
	
	
	public CustomMenu findByWxIdAndEventKey(String wxId,String eventKey){
		CustomMenu cmenu = null;
		cmenu =  customMenuDaoImp.findByWxIdAndEventKey(wxId, eventKey);
		return cmenu;
	}
	
}
