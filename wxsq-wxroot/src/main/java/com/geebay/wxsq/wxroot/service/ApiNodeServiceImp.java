package com.geebay.wxsq.wxroot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.common.api.service.ApiNodeService;
import com.geebay.wxsq.model.account.base.ApiNode;
import com.geebay.wxsq.wxroot.dao.ApiNodeDaoImp;

@Service(value="wxroot_apiNodeServiceImp")
public class ApiNodeServiceImp implements ApiNodeService{

	@Autowired
	private ApiNodeDaoImp apiNodeDaoImp;
	
	public ApiNode  findById(String  id){
		return apiNodeDaoImp.find(id);
	}
	
}
