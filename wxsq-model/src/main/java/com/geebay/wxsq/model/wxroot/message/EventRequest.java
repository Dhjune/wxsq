package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.wxroot.WxMsgRequest;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="eventRequest")
public class EventRequest implements WxMsgRequest{
	
	@XmlElement
    private String ToUserName;
	 
    @XmlElement
    private String FromUserName;
    
    @XmlElement
    private String CreateTime;
    
    @XmlElement
    private String MsgType;
    
    @XmlElement
    private String Event;  
    
    @XmlElement
    private String EventKey;
    
    @XmlElement
    private String Latitude;
    
    @XmlElement
    private String Longitude;
    
    @XmlElement
    private String Precision;
    
    
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
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
}
