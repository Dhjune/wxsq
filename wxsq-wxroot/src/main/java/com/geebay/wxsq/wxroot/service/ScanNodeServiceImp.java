package com.geebay.wxsq.wxroot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geebay.wxsq.common.api.service.ScanNodeService;
import com.geebay.wxsq.model.account.base.ScanNode;
import com.geebay.wxsq.wxroot.dao.ScanNodeDaoImp;

@Service(value="wxroot_scanNodeServiceImp")
public class ScanNodeServiceImp implements ScanNodeService{

	@Autowired
	private ScanNodeDaoImp scanNodeDaoImp;
	
	public ScanNode findByWxIdAndScanId(String wxId,String scanId){
		return  scanNodeDaoImp.findByWxIdAndScanId(wxId,scanId);
	}
	
}
