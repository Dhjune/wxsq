package com.geebay.wxsq.wxroot.service.eventflow;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.geebay.wxsq.wxroot.plugin.BaseServicePlugin;


@Component
public class ServiceMachine implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		ServiceMachine.applicationContext = applicationContext;
		
	}
	
	public static BaseServicePlugin getWeixinServicePluginByCode(String serviceCode) {
		BaseServicePlugin plugin = null;		
        Map<String, BaseServicePlugin> beans = applicationContext.getBeansOfType(BaseServicePlugin.class);
        Collection c = beans.values();
        Iterator it = c.iterator();              
        for (; it.hasNext();)  {
        	plugin = (BaseServicePlugin) it.next();
        	
        	if(StringUtils.equals(plugin.getServiceCode(),serviceCode)){
        		
        		return plugin;
        		
        	}           
        }
        return plugin;    
        
    }

}
