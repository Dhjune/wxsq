package com.geebay.wxsq.common.mongo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

public class MongoResolver extends MongoAbstract{ 
	
	
	private MongoOperations operations;
	
	public  <T,K>  List<T>  list(Map map ,K k, T last){
		
			
			return null;
		
	}
	
	public void  detection(String str){
		
	}
	
	
}
