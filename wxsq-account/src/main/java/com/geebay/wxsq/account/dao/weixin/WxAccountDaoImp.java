package com.geebay.wxsq.account.dao.weixin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.common.api.dao.WxAccountDao;
import com.geebay.wxsq.model.account.base.WxAccount;


@Repository(value="account_wxAccountDaoImp")
public class WxAccountDaoImp implements WxAccountDao{
	@Autowired
	private MongoOperations operations;
	
	public void save(WxAccount wxaccout){
		operations.save(wxaccout);
	}

	public List<WxAccount> list(String wxsqUserId) {
		
		return null;
	}

	public List<WxAccount> list(Map map, String wxsqUserId, String lastKey) {
		
		Query query  = new Query();
		Direction direction=true?Direction.ASC:Direction.DESC;
		
		
		
		return null;
	}
}
