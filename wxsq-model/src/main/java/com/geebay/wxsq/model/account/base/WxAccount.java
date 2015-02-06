package com.geebay.wxsq.model.account.base;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=WxAccount.COLLECTION)
public class WxAccount {
	
	public static final String COLLECTION = "wxsq_weixin_account";
	
	@Id
	private String  id;
	
	private String weixinName;
	
	private String wxId;  //微信原始ID
	
	private String weixinType;
	
	private String weixinNum;  //如june_12345
	
	private String wxsqUserId;
			
	private Date createTime;
	
	private Date modifyTime;
	
	private String wxAccountName;
	
    private String qrcode; //二维码
    
    private String signature;  //接入返回码，不重要。可以不保存
    
    private String location; //以后具体到实体location封装    
  
    private String token;
    
    
    private String appId;
    
    private String appSecret;
    
    private ReplyProvider replayProvider;
    
	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

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

	public String getWxAccountName() {
		return wxAccountName;
	}

	public void setWxAccountName(String wxAccountName) {
		this.wxAccountName = wxAccountName;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
    
	public static String getCollection() {
		return COLLECTION;
	}
	
	public ReplyProvider getReplayProvider() {
		return replayProvider;
	}

	public void setReplayProvider(ReplyProvider replayProvider) {
		this.replayProvider = replayProvider;
	}

	public String getWxsqUserId() {
		return wxsqUserId;
	}

	public void setWxsqUserId(String wxsqUserId) {
		this.wxsqUserId = wxsqUserId;
	}

	public String getWeixinType() {
		return weixinType;
	}

	public void setWeixinType(String weixinType) {
		this.weixinType = weixinType;
	}

	public String getWeixinName() {
		return weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}

	public String getWeixinNum() {
		return weixinNum;
	}

	public void setWeixinNum(String weixinNum) {
		this.weixinNum = weixinNum;
	}

	class ReplyProvider{
		private String  type = "text";
		
		private String replayId ="";  //serviceId or messageId
		
		private String serviceUrl;  //第三方服务接口
		
		private String  content = "您好，欢迎关注~"; //response COntent,serviceName or messageTitle

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getReplayId() {
			return replayId;
		}

		public void setReplayId(String replayId) {
			this.replayId = replayId;
		}

		public String getServiceUrl() {
			return serviceUrl;
		}

		public void setServiceUrl(String serviceUrl) {
			this.serviceUrl = serviceUrl;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
	
}
