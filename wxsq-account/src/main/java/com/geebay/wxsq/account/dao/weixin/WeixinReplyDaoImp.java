package com.geebay.wxsq.account.dao.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.weixin.WeixinReplyDao;
import com.geebay.wxsq.model.account.base.WxMessage;

@Repository(value="account_weixinreplydaoimp")
public class WeixinReplyDaoImp implements WeixinReplyDao{
	
	@Autowired
	private MongoOperations operations;

	public void save(WxMessage wxMessage) throws Exception{
		
		operations.save(wxMessage);
		
		
	}
	
}
