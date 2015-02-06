package com.geebay.wxsq.wxroot.plugin;

import java.util.Map;

import com.geebay.wxsq.model.wxroot.PassiveResult;

public interface BaseServicePlugin {
	
	public static final String M_SERVICE_HOST = "http://www.geebay.cn";
	//微信用户
	public static String CTX_KEY_WEIXIN_USER = "wxUser";
    public static String CTX_KEY_WEIXIN_USER_OPENID = "OpenId";
    public static String CTX_KEY_USER_INPUT = "userInput";
    public static String CTX_KEY_USER_ORI_INPUT = "userOriInput";
    public static String CTX_KEY_USER_INPUT_TYPE = "userInputType";  //事件,文本，图片，地址信息
    public static String CTX_KEY_WEIXIN_ID = "wxId";
    //官方后台wxapp_id;
    public static String CTX_KEY_WX_APP_ID = "wxAppId";
    //服务后台，注册微信号后产生的Id;
    public static String CTX_KEY_WX_ACCOUNT_ID ="wxAccountId";
    //社区用户 (包含注册使用的后台用户，微信绑定用户，或者开发者用户)
    public static String CTX_KEY_COMMUNITY_USER ="User";
    
    public static String CTX_KEY_EVENT_TYPE = "eventType";
    
    public static String CTX_KEY_EVENT_KEY = "eventKey";
    
    
    public void init();
    
    public String getServiceCode();

    public PassiveResult execute(Map<String, Object> context);
    
    
    
}
