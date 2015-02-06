package com.geebay.wxsq.wxroot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.geebay.wxsq.model.account.base.ReplyNode;
import com.geebay.wxsq.model.account.base.ScanNode;

@Repository(value="wxroot_ScanNodeDaoImp")
public class ScanNodeDaoImp {
	
	@Autowired
	private MongoOperations operations;

	public ScanNode findByWxIdAndScanId(String wxId, String scanId) {
		ScanNode scan = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("wxId").is(wxId).and("scanId").is(scanId));
		scan =  operations.findOne(query, ScanNode.class);
		return scan;
	}
	
}
