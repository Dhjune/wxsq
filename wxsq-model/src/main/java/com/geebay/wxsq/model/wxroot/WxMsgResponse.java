package com.geebay.wxsq.model.wxroot;

/**
 * 返回微信后台通用消息模型
 * 
 * @author dhjune
 *
 */
public interface WxMsgResponse {
	
	public String getMsgType();

    public String getFromUserName();

    public String getToUserName();

    public String getCreateTime();
    
    public String getResponseHead();

}
