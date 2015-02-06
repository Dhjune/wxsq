package com.geebay.wxsq.account.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxsqUserDao;
import com.geebay.wxsq.model.account.base.WxMessage;
import com.geebay.wxsq.model.account.base.WxsqUser;

@Repository(value="account_wxsqUserDaoImp")
public class WxsqUserDaoImp implements WxsqUserDao{
	
	@Autowired
	private MongoOperations operations;
	
	
	public void save(WxsqUser wxsqUser){
		
		operations.save(wxsqUser);	
		
	}
	
	public boolean exist(String userName){
		WxsqUser wxsqUser = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		wxsqUser = operations.findOne(query, WxsqUser.class);
		if(wxsqUser!=null){
			return true;
		}
		return false;
	}
	
	public Map login(String userName,String password){
		WxsqUser wxsqUser = null;
		Map map = new HashMap();
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		wxsqUser = operations.findOne(query, WxsqUser.class);
		System.out.println(operations.findOne(query, WxsqUser.class));
		if(wxsqUser==null){
			map.put("success", false);
			map.put("message", "用户名不存在 ！");
		}else{
			if(wxsqUser.getPassword().equals(password)){
				map.put("success", true);
				map.put("message", "登录成功");
				map.put("wxsqUser", wxsqUser);
			}else{
				map.put("success", false);
				map.put("message", "密码错误！");
			}
		}
		return map;
	}

	public void findByName(String userName) {
		// TODO Auto-generated method stub
		
	}

}
