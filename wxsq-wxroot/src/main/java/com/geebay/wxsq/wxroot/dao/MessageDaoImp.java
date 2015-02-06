package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.MessageDao;
import com.geebay.wxsq.model.account.base.ReplyNode;
import com.geebay.wxsq.model.account.base.WxMessage;
import com.geebay.wxsq.model.account.base.WxMessagePackage;

@Repository(value="wxroot_messageDaoImp")
public class MessageDaoImp implements MessageDao{

	@Autowired
	private MongoOperations operations;
	
	
	public WxMessage findMessage(String id){
		WxMessage message = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		message = operations.findOne(query, WxMessage.class);		
		return  message;
	}
	
	public WxMessagePackage findMessagePackage(String id){
		WxMessagePackage  msgPk = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		msgPk = operations.findOne(query, WxMessagePackage.class);				
		return  msgPk;
	}
	
}
