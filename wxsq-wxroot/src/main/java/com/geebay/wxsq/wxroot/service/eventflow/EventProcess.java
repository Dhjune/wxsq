package com.geebay.wxsq.wxroot.service.eventflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geebay.wxsq.common.http.HttpWorkService;
import com.geebay.wxsq.model.account.base.ApiNode;
import com.geebay.wxsq.model.account.base.ReplyNode;
import com.geebay.wxsq.model.account.base.ScanNode;
import com.geebay.wxsq.model.account.base.ServicePlugin;
import com.geebay.wxsq.model.account.base.WxMessage;
import com.geebay.wxsq.model.account.base.WxMessagePackage;
import com.geebay.wxsq.model.account.base.WxMessagePackage.MessageItem;
import com.geebay.wxsq.model.account.weixin.CustomMenu;
import com.geebay.wxsq.model.user.WxUser;
import com.geebay.wxsq.model.wxroot.PassiveDataItem;
import com.geebay.wxsq.model.wxroot.PassiveResult;
import com.geebay.wxsq.wxroot.dao.EventNodeDaoImp;
import com.geebay.wxsq.wxroot.dao.MessageDaoImp;
import com.geebay.wxsq.wxroot.dao.WxAccountDaoImp;
import com.geebay.wxsq.wxroot.dao.WxUserDaoImp;
import com.geebay.wxsq.wxroot.plugin.BaseServicePlugin;
import com.geebay.wxsq.wxroot.service.ApiNodeServiceImp;
import com.geebay.wxsq.wxroot.service.CustomMenuServiceImp;
import com.geebay.wxsq.wxroot.service.PluginsServiceImp;
import com.geebay.wxsq.wxroot.service.ScanNodeServiceImp;

@Component
public class EventProcess {
	
	@Autowired
	private EventNodeDaoImp eventNodeDaoImp;
	
	@Autowired
	private MessageDaoImp messageDaoImp;
	
	@Autowired
	private ServiceMachine serviceMachine;
	
	@Autowired
	private HttpWorkService httpWorkService;
	
	@Autowired
	private WxUserDaoImp wxUserDaoImp;
	
	@Autowired
	private WxAccountDaoImp  wxAccountDaoImp;
	
	@Autowired
	private CustomMenuServiceImp customMenuServiceImp;
	
	@Autowired
	private PluginsServiceImp pluginsServiceImp;
	
	@Autowired 
	private ScanNodeServiceImp scanNodeServiceImp;
	
	@Autowired
	private ApiNodeServiceImp apiNodeServiceImp;
	
	
	public void Workflow(String type,String operateId,String textContent,String openId,EventContext context){
		//String type = event.getType();
		PassiveResult result = null ;
		List<PassiveDataItem> list = new ArrayList<PassiveDataItem>();
		if(type.equals("text")){		
			result = new PassiveResult();
			result.setType("text");
			PassiveDataItem dataItem = new PassiveDataItem();
		    dataItem.setDescription(textContent);	
		    list.add(dataItem);
		    result.setDataItems(list);
		}else if(type.equals("news")){
			result = new PassiveResult();
			//String msgId = event.getMsgId();
			WxMessage message = messageDaoImp.findMessage(operateId);
			result.setType(PassiveResult.WEIXIN_MESSAGE_TYPE_NEWS);
			PassiveDataItem dataItem = new PassiveDataItem();
			dataItem.setTitle(message.getTitle());
			dataItem.setImageUrl(message.getImageUrl());
			dataItem.setDescription(message.getDescription());
						
			String url ="";
			if(message.getUrl()==null||message.getUrl().equals("")){				
				url = String.format("%s/message/view?msgId=%s&wxId=%s&openId=%s", BaseServicePlugin.M_SERVICE_HOST,message.getId(),message.getWxId(),openId);
			}else {				
				url = message.getUrl();
			}
			dataItem.setUrl(url);
			list.add(dataItem);	
			result.setDataItems(list);
		}else if(type.equals("package")){
			result = new PassiveResult();
		//	String packageId = event.getMsgPackageId();
			WxMessagePackage msgPackage = messageDaoImp.findMessagePackage(operateId);
			List<MessageItem> msgs =  msgPackage.getMsgItems();
			for(MessageItem msg: msgs){
				PassiveDataItem dataItem = new PassiveDataItem();
				dataItem.setTitle(msg.getTitle());
				dataItem.setDescription(msg.getDescription());
				dataItem.setImageUrl(msg.getImageUrl());
				String url = "";
				if(msg.getUrl()==null||msg.getUrl().equals("")){					
					url = String.format("%s/message/view?msgId=%s&wxId=%s&openId=%s", BaseServicePlugin.M_SERVICE_HOST,msg.getMsgId(),msgPackage.getWxId(),openId);
				}else {
					url = msg.getUrl();		
				}
				dataItem.setUrl(url);
				list.add(dataItem);
			}			
			result.setDataItems(list);			
		}else if(type.equals("service")){
		
			ServicePlugin plugin =  pluginsServiceImp.findById(operateId);
			BaseServicePlugin service = serviceMachine.getWeixinServicePluginByCode(plugin.getServiceCode());
			if(service!=null){
				result = service.execute(context.getContext());
			}
			
		//DIY  api模块调用	还是暂时撤销吧~
		}else if(type.equals("api")){
			//result = new PassiveResult();
			
			//ApiNode api =  apiNodeServiceImp.findById(operateId);
						
			//httpWorkService.getJsonService(api.getSendMap(), null, api.getUrl());
			
			//api  外部引用模块,暂时定为在后台生成webPlugin表，用来定义外部的模块
			//httpWorkService.getJsonService(params, files, event.getWebService());
		}
		context.getContext().put("result", result);
		
	}
	
	
	
	public void execEvent(EventContext context){
		
		String type = (String) context.getContext().get(BaseServicePlugin.CTX_KEY_EVENT_TYPE);
		String openId =  (String)context.getContext().get(BaseServicePlugin.CTX_KEY_WEIXIN_USER_OPENID);
		String wxId =  (String) context.getContext().get(BaseServicePlugin.CTX_KEY_WEIXIN_ID);
		String wxAccountId =  (String) context.getContext().get(BaseServicePlugin.CTX_KEY_WX_ACCOUNT_ID);
		WxUser wxUser = null;
		String operateId = "";
		String  content = "";
		if(type.equals("subscribe")){
			
			wxUser = wxUserDaoImp.findWxUserByOpenIdAndWxId(openId, wxId);
			if(wxUser == null){
				wxUser = new WxUser();
				wxUser.setOpenId(openId);
				wxUser.setWxId(wxId);
				wxUser.setWxAccountId(wxAccountId);
				wxUser.setCreateTime(new Date());
				wxUserDaoImp.save(wxUser);
			}
			
		}else if(type.equals("unsubscribe")){
			
			//wxUser = wxUserDaoImp.findWxUserByOpenIdAndWxId(openId, wxId);
			wxUserDaoImp.disableWxUser(openId, wxId);
			
		}else if(type.equals("SCAN")){
			
			String eventKey  = (String) context.getContext().get(BaseServicePlugin.CTX_KEY_EVENT_KEY);
			ScanNode scan = scanNodeServiceImp.findByWxIdAndScanId(wxId, eventKey);
			String execType = scan.getExecType();
			operateId = scan.getOperateId();
			Workflow(execType, operateId, content, openId, context);
			
			
		}else if(type.equals("CLICK")){
			
			String eventKey  = (String) context.getContext().get(BaseServicePlugin.CTX_KEY_EVENT_KEY);
			CustomMenu cmenu = customMenuServiceImp.findByWxIdAndEventKey(wxId, eventKey);
			String execType = cmenu.getEventType();
			operateId = cmenu.getExecId();
			Workflow(execType, operateId,content , openId, context);
			
			
		}else if(type.equals("VIEW")){
			
			
			
		}else if(type.equals("LOCATION")){
			
			
			
		}
		
		
	}
	
	public void execText(EventContext context){
		String value = (String) context.getContext().get(BaseServicePlugin.CTX_KEY_USER_INPUT);
		String wxAccountId = (String)context.getContext().get(BaseServicePlugin.CTX_KEY_WX_ACCOUNT_ID);
		String content = (String)context.getContext().get(BaseServicePlugin.CTX_KEY_USER_INPUT);
		String openId = (String)context.getOpenId();
		ReplyNode event =  eventNodeDaoImp.find(wxAccountId, content);
		String type = event.getReplyType();
		String operateId = "";
		if(type.equals("news")){
			
			operateId = event.getMsgId();
			
		}else if(type.equals("package")){
			
			operateId = event.getMsgPackageId();
			
		}else if(type.equals("service")){
			
			operateId = event.getServicePluginId();
			
		}else if(type.equals("api")){
			
			operateId = event.getWebService();
		}
		
		Workflow(type, operateId, event.getReplyContent(), openId, context);
		
	}
	
	public void execImage(EventContext context){
		
		
		
	}
	
	public void execVoice(EventContext context){
		
		
		
	}
	
	public void execVedio(EventContext context){
		
		
		
	}
	
	public void execLocation(EventContext context){
		
		
		
	}

	public void execLink(EventContext context) {
		
		
		
	}

	public EventNodeDaoImp getEventNodeDaoImp() {
		return eventNodeDaoImp;
	}

	public void setEventNodeDaoImp(EventNodeDaoImp eventNodeDaoImp) {
		this.eventNodeDaoImp = eventNodeDaoImp;
	}
	
}
