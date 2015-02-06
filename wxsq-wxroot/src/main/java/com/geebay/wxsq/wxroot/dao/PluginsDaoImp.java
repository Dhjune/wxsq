package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.PluginsDao;
import com.geebay.wxsq.model.account.base.ServicePlugin;


@Repository(value="wxroot_pluginsDaoImp")
public class PluginsDaoImp implements PluginsDao{
	
	@Autowired
	private MongoOperations operations;
	
	public ServicePlugin findByWxIdAndServiceCode(String wxId,String serviceCode){
		ServicePlugin plugin = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxId").is(wxId).and("serviceCode").is(serviceCode));
		plugin =  operations.findOne(query, ServicePlugin.class);
		return  plugin;
	}

	public ServicePlugin findById(String id) {
		ServicePlugin plugin = null;
		Query query = new Query(Criteria.where("_id").is(id));
		plugin = operations.findOne(query, ServicePlugin.class);
		return plugin;
	}

}
