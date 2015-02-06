package com.geebay.wxsq.account.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.MessageDao;
import com.geebay.wxsq.model.account.base.ReplyNode;
import com.geebay.wxsq.model.account.base.WxMessage;
import com.geebay.wxsq.model.account.base.WxMessagePackage;

@Repository
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
	
	public List<WxMessage> listMessage(String wxAccountId,int pageSize,int start,Map map){
		
		List<WxMessage> list = null;
		Query query  = new Query();
		Direction direction=true?Direction.ASC:Direction.DESC;
		String  createTime = (String) map.get("createTime");
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId).and("createTime").gte(createTime)).with(new Sort(direction,"createTime")).limit(pageSize);
		list = operations.find(query, WxMessage.class);
		return list;
		
	}
	
	
	public WxMessagePackage findMessagePackage(String id){
		WxMessagePackage  msgPk = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		msgPk = operations.findOne(query, WxMessagePackage.class);				
		return  msgPk;
	}
	/**
	 * 
	 * @param wxAccountId
	 * @param args 
	 * @return
	 */
	public List<WxMessagePackage> listMessagePackage(String wxAccountId,String ... args){
		List<WxMessagePackage> list = null;
		Direction direction=true?Direction.ASC:Direction.DESC;
		Query query =new Query();
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId)).with(new Sort(direction,"createTime")).limit(20);
		list = operations.find(query, WxMessagePackage.class);
		
		return list;
	}
	
	/**
	 * 
	 * @param wxAccountId
	 * @param pageSize
	 * @param start
	 * @param map 包含一些查询条件，包括默认时间分页排序查找条件
	 * @return
	 */
	public List<WxMessagePackage> listMessagePackage(String wxAccountId,int pageSize,int start,Map map){
		
		List<WxMessagePackage> list = null;
		Direction direction=true?Direction.ASC:Direction.DESC;
		Query query =new Query();
		String  createTime = (String) map.get("createTime");
		query.addCriteria(Criteria.where("wxAccountId").is(wxAccountId).and("createTime").gte(createTime)).with(new Sort(direction,"createTime")).limit(pageSize);
		list = operations.find(query, WxMessagePackage.class);
		return list;
		
	}
	
}
