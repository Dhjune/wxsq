package com.geebay.wxsq.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.EventNodeDao;
import com.geebay.wxsq.model.account.base.ReplyNode;

@Repository
public class EventNodeDaoImp implements EventNodeDao{
	
	@Autowired
	private MongoOperations operations;
	
	
	public ReplyNode find(String id){
		ReplyNode event = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		event = operations.findOne(query, ReplyNode.class);
		return event;
	}
	
	public ReplyNode find(String wxAccountId,String key){
		ReplyNode event = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId).and("key").is(key));
		event =  operations.findOne(query, ReplyNode.class);
		return event;
	}
	
	public List<ReplyNode> list(String wxAccountId,String ... rags){
		List<ReplyNode> list = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId));
		list = operations.find(query, ReplyNode.class);
		return  list;
	}
}
