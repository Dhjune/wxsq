package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.wxroot.WxMsgRequest;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="imagerequest")
public class ImageRequest implements WxMsgRequest{
	
	@XmlAnyElement
	private String ToUserName;
	
	@XmlAnyElement
	private String FromUserName;
	
	@XmlAnyElement
	private String CreateTime;
		
	@XmlAnyElement
	private String MsgType;
	
	@XmlAnyElement
	private String MediaId;
	
	@XmlAnyElement
	private String MsgId;
	
	@XmlAnyElement
	private String PicUrl;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
