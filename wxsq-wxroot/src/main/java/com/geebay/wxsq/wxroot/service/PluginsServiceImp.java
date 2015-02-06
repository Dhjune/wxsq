package com.geebay.wxsq.wxroot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.common.api.service.PluginsService;
import com.geebay.wxsq.model.account.base.ServicePlugin;
import com.geebay.wxsq.wxroot.dao.PluginsDaoImp;

@Service
public class PluginsServiceImp implements PluginsService{
	
	@Autowired
	private  PluginsDaoImp pluginsDaoImp;
	
	public ServicePlugin findById (String  id){
		return  pluginsDaoImp.findById(id);
	}
	
	public ServicePlugin findByWxIdAndServiceCode(String wxId,String serviceCode){
		
		return  pluginsDaoImp.findByWxIdAndServiceCode(wxId, serviceCode);
		
	}
	
	
}
