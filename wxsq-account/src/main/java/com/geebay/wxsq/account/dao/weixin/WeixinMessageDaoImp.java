package com.geebay.wxsq.account.dao.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geebay.wxsq.common.api.dao.weixin.WeixinMessageDao;

@Repository(value="account_weixinMessageDaoImp")
public class WeixinMessageDaoImp implements WeixinMessageDao{
	
	@Autowired
	private  MongoOperations mongoOperations;
	
	
	
	
	
	
	
}
