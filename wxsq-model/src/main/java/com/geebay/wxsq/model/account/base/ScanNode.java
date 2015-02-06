package com.geebay.wxsq.model.account.base;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=ScanNode.COLLECTION)
public class ScanNode {
	
	public static final String COLLECTION = "wxsq_weixin_scanNode";
	
	@Id
	private String  id;
	
	private String actionName; //QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	
	private String expireTime;  //零时二维码有效时间，以秒为单位。 最大不超过1800。
	
	private String description;
	
	private String wxId;
	
	private String wxAccontId;
	
	private String ticket;
	
	private String scanId;
	
	private String execType;
	
	private String operateId;
	
	private Date createTime;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getTicket() {
		return ticket;
	}


	public void setTicket(String ticket) {
		this.ticket = ticket;
	}


	public String getScanId() {
		return scanId;
	}


	public void setScanId(String scanId) {
		this.scanId = scanId;
	}


	public String getWxId() {
		return wxId;
	}


	public void setWxId(String wxId) {
		this.wxId = wxId;
	}


	public String getWxAccontId() {
		return wxAccontId;
	}


	public void setWxAccontId(String wxAccontId) {
		this.wxAccontId = wxAccontId;
	}


	public String getExecType() {
		return execType;
	}


	public void setExecType(String execType) {
		this.execType = execType;
	}


	public static String getCollection() {
		return COLLECTION;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public String getExpireTime() {
		return expireTime;
	}


	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}


	public String getOperateId() {
		return operateId;
	}


	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
}
