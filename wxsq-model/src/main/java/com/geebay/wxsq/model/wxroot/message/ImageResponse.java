package com.geebay.wxsq.model.wxroot.message;

import javax.xml.bind.annotation.XmlElement;

import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.wxroot.WxMsgRequest;


public class ImageResponse implements WxMsgRequest{

	 @XmlElement
	    private String ToUserName;
	    
	    @XmlElement
	    private String FromUserName;
	    
	    @XmlElement
	    private String CreateTime;
	    
	    @XmlElement
	    private String MsgType = Constants.WEIXIN_MESSAGE_TYPE_IMAGE;

	    @XmlElement
	    private String MediaId;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}

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

	

}
