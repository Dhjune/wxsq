package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxRootDao;
import com.geebay.wxsq.model.account.base.WxAccount;
import com.geebay.wxsq.model.user.WxUser;

@Repository(value="wxroot_wxrootDaoImp")
public class WxRootDaoImp implements WxRootDao{
	
	@Autowired
	private MongoOperations operations;
	
	public WxAccount findWxAccountById(String  id){
		
		WxAccount wxAccount = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		wxAccount = operations.findOne(query, WxAccount.class);
		return  wxAccount ;
		
	}
	
	public WxUser findWxUserByOpenIdAndWxId(String openId,String wxId){
		WxUser  wxUser = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("openId").is(openId).and("wxId").is(wxId));
		wxUser = operations.findOne(query, WxUser.class);
		return wxUser;
	}
	
}
