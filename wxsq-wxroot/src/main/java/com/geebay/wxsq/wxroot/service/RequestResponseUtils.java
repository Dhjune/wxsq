package com.geebay.wxsq.wxroot.service;

import org.apache.commons.lang3.StringUtils;

import com.geebay.wxsq.model.Constants;


public class RequestResponseUtils {
	
	
	 public static String getRequestType(String body) {
	        if (StringUtils.indexOf(body, "<MsgType><![CDATA[text]]></MsgType>") != -1) {
	            return Constants.WEIXIN_MESSAGE_TYPE_TEXT;
	        } else if (StringUtils.indexOf(body, "<MsgType><![CDATA[image]]></MsgType>") != -1) {
	            return Constants.WEIXIN_MESSAGE_TYPE_IMAGE;
	        } else if (StringUtils.indexOf(body, "<MsgType><![CDATA[location]]></MsgType>") != -1) {
	            return Constants.WEIXIN_MESSAGE_TYPE_LOCATION;
	        } else if (StringUtils.indexOf(body, "<MsgType><![CDATA[link]]></MsgType>") != -1) {
	            return Constants.WEIXIN_MESSAGE_TYPE_LINK;
	        } else if (StringUtils.indexOf(body, "<MsgType><![CDATA[event]]></MsgType>") != -1) {
	            return Constants.WEIXIN_MESSAGE_TYPE_EVENT;
	        }
	        return null;
	    }
}
