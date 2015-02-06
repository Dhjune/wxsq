package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.ApiNodeDao;
import com.geebay.wxsq.model.account.base.ApiNode;
import com.geebay.wxsq.model.account.base.ReplyNode;

@Repository(value="wxroot_apiNodeDaoImp")
public class ApiNodeDaoImp implements ApiNodeDao{

	@Autowired
	private MongoOperations operations;
	
	public ApiNode find(String id){
		ApiNode api = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		api = operations.findById(id, ApiNode.class);		
		return api;
		
	}
	
}
