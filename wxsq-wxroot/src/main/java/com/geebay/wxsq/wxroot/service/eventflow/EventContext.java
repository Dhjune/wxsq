package com.geebay.wxsq.wxroot.service.eventflow;

import java.util.Map;




public class EventContext {
	
	private String openId;
	
	private String wxId;
	
	private Map<String, Object> context;

	public EventContext(String openId, String wxId,
			Map<String, Object> context) {
		this.openId = openId;
		this.wxId = wxId;
		this.context = context;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

}
