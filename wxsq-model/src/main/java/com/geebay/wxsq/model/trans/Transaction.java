package com.geebay.wxsq.model.trans;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=Transaction.COLLECTION)
public class Transaction {
	
	public static final String COLLECTION = "wxsq_transaction_qreue";
	
	@Id
	private String id;
	
	private String sId;
	
	private String dId;
	
	private String sName;
	
	private String dName;
	
	private Map values;
	
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public Map getValues() {
		return values;
	}

	public void setValues(Map values) {
		this.values = values;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}  
	
	public static String getCollection() {
		return COLLECTION;
	}
}
