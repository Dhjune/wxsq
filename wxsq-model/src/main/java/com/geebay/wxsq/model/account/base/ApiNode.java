package com.geebay.wxsq.model.account.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=ApiNode.COLLECTION)
public class ApiNode {
	
	public static final String COLLECTION = "wxsq_weixin_apiNode";
	
	@Id
	private String id;
	
	private String wxId;
	
	private String wxAccountId;
	
	private String description;
	
	private int status;
	
	private String url; //接口url
	
	private Date  createTime;
	
	private Date modifyTime;
	
	private Map sendMap = new HashMap();
	
	private Map getMap = new HashMap();
	
	private String sendtype;
	
	private String gettype;
	
	private String wxMessageType;
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getWxId() {
		return wxId;
	}



	public void setWxId(String wxId) {
		this.wxId = wxId;
	}



	public String getWxAccountId() {
		return wxAccountId;
	}



	public void setWxAccountId(String wxAccountId) {
		this.wxAccountId = wxAccountId;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getModifyTime() {
		return modifyTime;
	}



	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}



	public static String getCollection() {
		return COLLECTION;
	}



	public Map getSendMap() {
		return sendMap;
	}



	public void setSendMap(Map sendMap) {
		this.sendMap = sendMap;
	}



	public Map getGetMap() {
		return getMap;
	}



	public void setGetMap(Map getMap) {
		this.getMap = getMap;
	}


	public String getWxMessageType() {
		return wxMessageType;
	}



	public void setWxMessageType(String wxMessageType) {
		this.wxMessageType = wxMessageType;
	}



	public String getGettype() {
		return gettype;
	}



	public void setGettype(String gettype) {
		this.gettype = gettype;
	}



	public String getSendtype() {
		return sendtype;
	}



	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}
}
