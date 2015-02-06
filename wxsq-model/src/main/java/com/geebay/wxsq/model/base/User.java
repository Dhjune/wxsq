package com.geebay.wxsq.model.base;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=User.COLLECTION)
public class User {

	public static final String COLLECTION = "wxsq_user";
	
	@Id
	private String id;
	
	private String name;
	
	private String email;
	
	private String mobile;
	
	private String password;
	
	private Date createTime;
	
	private Date modifyDime;
	
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyDime() {
		return modifyDime;
	}

	public void setModifyDime(Date modifyDime) {
		this.modifyDime = modifyDime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public static String getCollection() {
		return COLLECTION;
	}
	
	
	
	
}
