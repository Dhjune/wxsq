package com.geebay.wxsq.account.service.weixin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.account.dao.weixin.WxAccountDaoImp;
import com.geebay.wxsq.common.api.service.WxAccountService;
import com.geebay.wxsq.model.account.base.WxAccount;

@Service(value="account_wxAccountServiceImp")
public class WxAccountServiceImp implements WxAccountService{
	
	
	@Autowired
	private WxAccountDaoImp wxAccountDaoImp;
	
	
	public List<WxAccount> list(String wxsqUserId){
		
		List<WxAccount> list = null;
		
		list = wxAccountDaoImp.list(wxsqUserId);
		
		return  list;
		
	}


	public List list(Map map, String wxsqUserId, String lastKey) {
		List<WxAccount> list = null;

		list = wxAccountDaoImp.list(map,wxsqUserId,lastKey);
		
		return  list;
		
		
	}
	

}
