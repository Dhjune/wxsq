package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.wxroot.WxMsgResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = MusicResponse.ResponseHead)
public class MusicResponse implements WxMsgResponse {
    public static final String ResponseHead = "musicResponse";
    //common
    
    @XmlElement
    private String ToUserName;
    @XmlElement
    private String FromUserName;
    @XmlElement
    private String CreateTime;
    @XmlElement
    private String MsgType = Constants.WEIXIN_MESSAGE_TYPE_MUSIC;
    @XmlElement
    private Music Music;
    //response
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

    public String getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(String funcFlag) {
        FuncFlag = funcFlag;
    }

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }

    @Override
    public String toString() {
        return "MusicResponse{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", Music=" + Music +
                ", FuncFlag='" + FuncFlag + '\'' +
                '}';
    }


    public String getResponseHead() {
        return ResponseHead;
    }
}
