package com.geebay.wxsq.account.service.weixin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.account.dao.weixin.WeixinReplyDaoImp;
import com.geebay.wxsq.common.api.service.weixin.WeixinReplyService;
import com.geebay.wxsq.model.account.base.WxMessage;

@Service(value="account_weixinReplyServiceImp")
public class WeixinReplyServiceImp implements WeixinReplyService{
	
	@Autowired
	private WeixinReplyDaoImp weixinReplyDaoImp;

	public boolean save(WxMessage wxMessage){
		try{
		    wxMessage.setCreateTime(new Date());
			weixinReplyDaoImp.save(wxMessage);
			return true;
		}catch(Exception e){
			
			return false;
		}		
		
	}
	
	
}
