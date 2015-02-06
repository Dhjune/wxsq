package com.geebay.wxsq.wxroot.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxUserDao;
import com.geebay.wxsq.model.user.WxUser;

@Repository(value="wxroot_wxUserDaoImp")
public class WxUserDaoImp implements WxUserDao{
	
	@Autowired
	private MongoOperations operations;
	
	public WxUser findWxUserByOpenIdAndWxId(String openId,String wxId){
		WxUser  wxUser = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("openId").is(openId).and("wxId").is(wxId));
		wxUser = operations.findOne(query, WxUser.class);
		return wxUser;
	}
	
	public void disableWxUser(String openId,String wxId){
		Query query = new Query(Criteria.where("openId").is(openId).and("wxId").is(wxId));
		Update update = new Update();
		update.set("status", 0);		
		operations.findAndModify(query, update, WxUser.class);
	}

	public void save(WxUser wxUser) {
		operations.save(wxUser);		
	}
	
}
