package com.geebay.wxsq.model.account.base;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * EventNode 用于自定义回复，关键词回复。
 * @author dhjune
 *
 */
@Document(collection=ReplyNode.COLLECTION)
public class ReplyNode {
	
	public static final String COLLECTION = "wxsq_weixin_replyNode";
	
	@Id
	private String id;

	private String wxAccountId;
	
	private String wxId;
	
	private String name;
	
	private String key;
	
	private String  replyType;  //类型  
	
	private String operateId;
	
	private String replyContent; //文本回复内容 
	
	private String msgId; //单一消息ID；
	
	private String msgTitle; //消息或者消息包组；
	
	private String msgPackageId; // 消息包ID；
	
	private String servicePluginId;
	
	private String serviceCode;
	
	private String serviceName; 
	
	private String webService;//此处保存webservice  url;
	
	
	private Date createTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getWxAccountId() {
		return wxAccountId;
	}

	public void setWxAccountId(String wxAccountId) {
		this.wxAccountId = wxAccountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getWebService() {
		return webService;
	}

	public void setWebService(String webService) {
		this.webService = webService;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getMsgPackageId() {
		return msgPackageId;
	}

	public void setMsgPackageId(String msgPackageId) {
		this.msgPackageId = msgPackageId;
	}
	
	public static String getCollection() {
		return COLLECTION;
	}

	public String getServicePluginId() {
		return servicePluginId;
	}

	public void setServicePluginId(String servicePluginId) {
		this.servicePluginId = servicePluginId;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	
}
