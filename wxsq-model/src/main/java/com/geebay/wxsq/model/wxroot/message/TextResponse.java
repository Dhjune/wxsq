package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.wxroot.WxMsgResponse;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name=TextResponse.ResponseHead)
public class TextResponse implements WxMsgResponse{
	public static final String ResponseHead = "textResponse";
   
    @XmlElement
    private String ToUserName;
    
    @XmlElement
    private String FromUserName;
    
    @XmlElement
    private String CreateTime;
    
    @XmlElement
    private String MsgType = Constants.WEIXIN_MESSAGE_TYPE_TEXT;

    @XmlElement
    private String Content;

    @XmlElement
    private String FuncFlag;

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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(String funcFlag) {
        FuncFlag = funcFlag;
    }

    @Override
    public String toString() {
        return "TextResponse{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", Content='" + Content + '\'' +
                ", FuncFlag='" + FuncFlag + '\'' +
                '}';
    }

    public String getResponseHead() {
        return ResponseHead;
    }
}