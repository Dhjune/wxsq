package com.geebay.wxsq.model.wxroot;

/**
 * 接收微信后台消息，通用模型
 * 
 * @author dhjune
 *
 */
public interface WxMsgRequest {
	
	public String getMsgType();

    public String getFromUserName();

    public String getToUserName();

    public String getCreateTime();

}
