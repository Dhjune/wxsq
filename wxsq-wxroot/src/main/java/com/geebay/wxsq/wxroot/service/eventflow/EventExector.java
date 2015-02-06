package com.geebay.wxsq.wxroot.service.eventflow;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.wxroot.plugin.BaseServicePlugin;


@Service
public class EventExector {
	
	@Autowired
	private EventProcess process;
	
	
	public void excute(EventContext context){
		
		String type = (String) context.getContext().get(BaseServicePlugin.CTX_KEY_USER_INPUT_TYPE);
		
		if(type.equals("text")){
			 process.execText(context);	
		}else if(type.equals("event")){
			 process.execEvent(context);	
		}else if(type.equals("image")){
			 process.execImage(context);	
		}else if(type.equals("location")){
			process.execLocation(context);	
		}else if(type.equals("video")){
			process.execVedio(context);	
		}else if(type.equals("voice")){
			process.execVoice(context);	
		}else if(type.equals("link")){
			process.execLink(context);	
		}		
	}

	public void execReply(){
		
	}
	

	public EventProcess getProcess() {
		return process;
	}

	public void setProcess(EventProcess process) {
		this.process = process;
	}
	
}
