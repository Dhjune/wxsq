package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.CustomMenuDao;
import com.geebay.wxsq.model.account.weixin.CustomMenu;

@Repository(value="wxroot_customMenuDaoImp")
public class CustomMenuDaoImp implements CustomMenuDao{
	
	@Autowired
	private MongoOperations operations;
	
	public void save(CustomMenu cmenu){
		
		operations.save(cmenu);
		
	}
	
	public CustomMenu findByWxIdAndEventKey(String wxId,String eventKey){
		CustomMenu cmenu = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxId").is(wxId).and("eventKey").is(eventKey));
		cmenu = operations.findOne(query, CustomMenu.class);
		return cmenu;
	}
	
	public void merge(CustomMenu cmenu){
		
	}
	
}
