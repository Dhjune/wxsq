package com.geebay.wxsq.model.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=WxUser.COLLECTION)
public class WxUser {
	
	public static final String COLLECTION = "wxsq_weixin_user";
	
	@Id
	private String id ;
	
	private String nickName;
	
	private String openId;
	
	private String wxAccountId;
	
	private String wxId;
	
	private Date createTime;
	
	private int status;
	
	private Date modifyTime;
	
	private String sex;
	
    private String city;
    
    private String province;
    
    private String country;
    
    private String wxHeadImgurl;
    
    private String mobile;
    
    private String email;
    
    private int bindingStatus = 0;   //0未绑定,1绑定未激活,2绑定激活
    
    private int score;
    
  
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxAccountId() {
		return wxAccountId;
	}

	public void setWxAccountId(String wxAccountId) {
		this.wxAccountId = wxAccountId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getBindingStatus() {
		return bindingStatus;
	}

	public void setBindingStatus(int bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	
	public static String getCollection() {
		return COLLECTION;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWxHeadImgurl() {
		return wxHeadImgurl;
	}

	public void setWxHeadImgurl(String wxHeadImgurl) {
		this.wxHeadImgurl = wxHeadImgurl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
