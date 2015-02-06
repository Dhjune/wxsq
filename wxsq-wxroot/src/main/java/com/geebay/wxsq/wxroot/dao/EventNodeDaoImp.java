package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.EventNodeDao;
import com.geebay.wxsq.model.account.base.ReplyNode;

@Repository(value="wxroot_eventNodeDaoImp")
public class EventNodeDaoImp implements EventNodeDao{
	
	@Autowired
	private MongoOperations operations;
	
	
	public ReplyNode find(String id){
		ReplyNode event = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		event = operations.findById(id, ReplyNode.class);
		//event = operations.findOne(query, EventNode.class);
		return event;
	}
	
	public ReplyNode find(String wxAccountId,String key){
		ReplyNode event = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId).and("key").is(key));
		event =  operations.findOne(query, ReplyNode.class);
		return event;
	}
}
