package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxAccountDao;
import com.geebay.wxsq.model.account.base.WxAccount;

@Repository(value="wxroot_wxAccountDaoImp")
public class WxAccountDaoImp implements WxAccountDao{

	@Autowired
	private MongoOperations operations;
	
	public WxAccount findById(String  id){
		
		WxAccount wxAccount = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		wxAccount = operations.findOne(query, WxAccount.class);
		return  wxAccount ;
		
	}
	
}
