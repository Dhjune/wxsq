package com.geebay.wxsq.account.dao.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxAccountDao;
import com.geebay.wxsq.model.account.base.WxAccount;


@Repository(value="account_wxAccountDaoImp")
public class WxAccountDaoImp implements WxAccountDao{
	@Autowired
	private MongoOperations operations;
	
	public void save(WxAccount wxaccout){
		operations.save(wxaccout);
	}
}
