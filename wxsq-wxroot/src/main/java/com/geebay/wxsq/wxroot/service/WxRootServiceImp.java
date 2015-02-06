package com.geebay.wxsq.wxroot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;







import com.geebay.wxsq.common.api.service.WxRootService;
import com.geebay.wxsq.config.MarshalAndUnmarshalService;
import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.account.base.WxAccount;
import com.geebay.wxsq.model.user.WxUser;
import com.geebay.wxsq.model.wxroot.PassiveResult;
import com.geebay.wxsq.model.wxroot.WxMsgRequest;
import com.geebay.wxsq.model.wxroot.WxMsgResponse;
import com.geebay.wxsq.model.wxroot.message.*;
import com.geebay.wxsq.wxroot.dao.WxAccountDaoImp;
import com.geebay.wxsq.wxroot.dao.WxRootDaoImp;
import com.geebay.wxsq.wxroot.dao.WxUserDaoImp;
import com.geebay.wxsq.wxroot.plugin.BaseServicePlugin;
import com.geebay.wxsq.wxroot.service.eventflow.EventContext;
import com.geebay.wxsq.wxroot.service.eventflow.EventExector;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

@Service
public class WxRootServiceImp implements WxRootService{
	
	@Autowired
	private WxRootDaoImp wxRootDaoImp;
	
	@Autowired
	private  EventExector exector;
	
	@Autowired
	private TransformService transformService;
	
	@Autowired
	private  MarshalAndUnmarshalService marshalAndUnmarshalService;
	
	@Autowired
	private WxUserDaoImp wxUserDaoImp;
	
	@Autowired
	private WxAccountDaoImp  wxAccountDaoImp;
	
	
	public String execute(String wxAccountId,String xmlBody) throws XmlMappingException, UnsupportedEncodingException{
		
		WxMsgRequest wxrequest= null;
		WxMsgResponse wxresp = null;
		WxUser wxUser = null;		
		WxAccount wxAccount = null;
		wxAccount = wxRootDaoImp.findWxAccountById(wxAccountId);
		String type = RequestResponseUtils.getRequestType(xmlBody);
		if(Constants.WEIXIN_MESSAGE_TYPE_TEXT.equals(type)){
			
			 xmlBody = StringUtils.replace(xmlBody, "<xml>", "<textRequest>");				 
			 xmlBody = StringUtils.replace(xmlBody, "</xml>", "</textRequest>");					 
			 wxrequest=  (TextRequest)((Jaxb2Marshaller) marshalAndUnmarshalService.getMarshaller()).unmarshal( new StreamSource(	new ByteArrayInputStream(xmlBody.getBytes("UTF-8"))));
			 String content=((TextRequest) wxrequest).getContent();
			 //saveWxUserMessage(wxrequest);
			 wxUser = wxRootDaoImp.findWxUserByOpenIdAndWxId(wxrequest.getFromUserName(), wxrequest.getToUserName());
			 
			
			 Map<String, Object> context = new HashMap<String, Object>();	
			 String openId = wxrequest.getFromUserName();				 
			 String wxId = wxrequest.getToUserName();			 
			//context.put(BaseServicePlugin.CTX_KEY_WEIXIN_USER, wxUser);				 
			 context.put(BaseServicePlugin.CTX_KEY_WEIXIN_USER_OPENID, wxrequest.getFromUserName());				 
			 context.put(BaseServicePlugin.CTX_KEY_USER_INPUT_TYPE, Constants.WEIXIN_MESSAGE_TYPE_TEXT);
			 context.put(BaseServicePlugin.CTX_KEY_USER_INPUT, content);
			 context.put(BaseServicePlugin.CTX_KEY_WX_ACCOUNT_ID, String.valueOf(wxAccount.getId()));
			 context.put(BaseServicePlugin.CTX_KEY_WX_APP_ID, wxAccount.getAppId());
			 context.put(BaseServicePlugin.CTX_KEY_WEIXIN_ID, wxId);
			 EventContext eventcontext= new EventContext(openId,wxId,context);				 
			 exector.excute(eventcontext);			 
			 wxresp = transformService.transResponse(wxrequest.getFromUserName(),wxId, (PassiveResult) eventcontext.getContext().get("result"));			 
		}else if(Constants.WEIXIN_MESSAGE_TYPE_EVENT.equals(type)) {
			 xmlBody = StringUtils.replace(xmlBody, "<xml>", "<eventRequest>");				 
			 xmlBody = StringUtils.replace(xmlBody, "</xml>", "</eventRequest>");
			 wxrequest= (EventRequest)((Jaxb2Marshaller) marshalAndUnmarshalService.getMarshaller()).unmarshal( new StreamSource(	new ByteArrayInputStream(xmlBody.getBytes("UTF-8"))));
			 String openId = wxrequest.getFromUserName();				 
			 String wxId = wxrequest.getToUserName();
			 Map<String, Object> context = new HashMap<String, Object>();	
			 context.put(BaseServicePlugin.CTX_KEY_WEIXIN_USER_OPENID, wxrequest.getFromUserName());				 
			 context.put(BaseServicePlugin.CTX_KEY_USER_INPUT_TYPE, Constants.WEIXIN_MESSAGE_TYPE_EVENT);	    				 
			 context.put(BaseServicePlugin.CTX_KEY_WX_ACCOUNT_ID, wxAccountId);
			 context.put(BaseServicePlugin.CTX_KEY_WEIXIN_ID, wxId);
			 context.put(BaseServicePlugin.CTX_KEY_EVENT_TYPE, ((EventRequest) wxrequest).getEvent());
			 context.put(BaseServicePlugin.CTX_KEY_EVENT_KEY, ((EventRequest) wxrequest).getEventKey());
			 EventContext eventcontext= new EventContext(openId,wxId,context);
			 exector.excute(eventcontext);
			 
		}else if(Constants.WEIXIN_MESSAGE_TYPE_LOCATION.equals(type)){
			
			//地理位置信息
			
		}else if(Constants.WEIXIN_MESSAGE_TYPE_LINK.equals(type)){
			
			//链接信息
			
		}
		//当为空时，做其他处理吧
		 if (wxresp == null) {
			 
		 }
		
		XMLSerializer serializer = getXMLSerializer();
		StringWriter bos = new StringWriter();
        SAXResult result = null;
        serializer.setOutputCharStream(bos);      
        try {
			result = new SAXResult(serializer.asContentHandler());
			if (wxresp != null) {
	        	marshalAndUnmarshalService.getMarshaller().marshal(wxresp, result);
	        }
		} catch (IOException e) {
			
			e.printStackTrace();
		}
      
        String respbody = null;
        
		try{		
			respbody = bos.toString();      
			respbody = StringUtils.replace(respbody, wxresp.getResponseHead(), "xml");							
            return new String(respbody.getBytes("ISO8859-1"),"UTF-8");
	            
        }catch (UnsupportedEncodingException e) {		        	
        	        	
            return null;
        } 
		
	}
	
	@SuppressWarnings("restriction")
	public  XMLSerializer getXMLSerializer() {
        OutputFormat of = new OutputFormat();
        of.setCDataElements(
                new String[]{"^ToUserName",
                        "^FromUserName",
                        "^Content",
                        "^MsgType",
                        "^Title",
                        "^Description",
                        "^MusicUrl",
                        "^HQMusicUrl",
                        "^PicUrl",
                        "^Url"});
        of.setPreserveSpace(true);
        of.setIndenting(true);
        of.setEncoding("UTF-8");        
        return new XMLSerializer(of);
    }
	
}
