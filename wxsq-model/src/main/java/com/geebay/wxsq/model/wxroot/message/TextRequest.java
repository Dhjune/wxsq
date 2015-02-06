package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.wxroot.WxMsgRequest;



@XmlRootElement(name = "textRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextRequest implements WxMsgRequest{
	
	@XmlElement
    private String ToUserName;
	@XmlElement
    private String FromUserName;
    @XmlElement
    private String CreateTime;
    @XmlElement
    private String MsgType;
    @XmlElement
    private String MsgId;
    @XmlElement
    private String Content;
	
	
    public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMsgType() {
		
		return MsgType;
	}

	public String getFromUserName() {
		
		return FromUserName;
	}
	public String getToUserName() {
		
		return ToUserName;
	}

	public String getCreateTime() {
		
		return CreateTime;
	}

	@Override
    public String toString() {
        return "TextRequest{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }


	
}
