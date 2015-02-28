package com.geebay.wxsq.common.mongo;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class MongoResolver {
	
	@Autowired
	private MongoOperations operations;
	
	public  <T>  List<T>  list(List<Expression> list , T target,int pageIndex,int pageSize,String url) throws NoSuchFieldException, SecurityException{
		Query query = new Query();
		List<T> result = null;
		if(list!=null &&list.size()>0){
			for(Iterator<Expression> i = list.iterator(); i.hasNext();)    { 
				 match(i.next(),query,target);
		    } 
		}
		 
		Direction direction = true?Direction.ASC:Direction.DESC;
		query.with(new Sort(direction,"createTime")).skip((pageIndex-1)*pageSize).limit(pageSize);		 
		result =  (List<T>) operations.find(query, target.getClass());
		 		 
		return result;
		
	}
	
	public  <T>  PageNav<T>  PageNavlist(List<Expression> list , T target,boolean isTotal,int pageIndex,int pageSize,String url) throws NoSuchFieldException, SecurityException{
		PageNav<T> context = null;
		
		Query query = new Query();
		if(list!=null &&list.size()>0){
			for(Iterator<Expression> i = list.iterator(); i.hasNext();)    { 
				 match(i.next(),query,target);
		    } 
		}
		
		 
		long total =  operations.count(query, target.getClass());
		
		Direction direction = true?Direction.ASC:Direction.DESC;
		
		query.with(new Sort(direction,"createTime")).skip((pageIndex-1)*pageSize).limit(pageSize);	
		
		List<T> result =  (List<T>) operations.find(query, target.getClass());
		
		context = new PageNav<T>(result, total, pageSize, pageIndex, url);
		
		return context;
		
	}

	@SuppressWarnings("static-access")
	public <T> void  match (Expression e,Query query ,T target) throws NoSuchFieldException, SecurityException{		
		Criteria criteria = null;
		if(e.initial_op!=null){
			
			
			switch (e.initial_op) {
			
		        case GT:
		        	criteria = new Criteria(e.name);
		        	criteria.gt(getField(target.getClass(), e.name, e.initial_v));          
		            break;
		        case LT:
		        	criteria = new Criteria(e.name);
		        	criteria.lt(getField(target.getClass(), e.name, e.initial_v));   
		            break;
		        case SORT:
		        	
		            Direction direction = true?Direction.ASC:Direction.DESC;
		   		    query.with(new Sort(direction,e.name));
		            break;
		        case LIMIT:
		        	query.limit(15);
		            break;
		        default:
		          
		            break;
	        }
		}
		
		if(e.trailing_op!=null){
		
			switch (e.trailing_op) {
			    case GT:
			    	
		        	criteria.gt(getField(target.getClass(), e.name, e.trailing_v));
			        break;
			        
			    case LT:
			    			    	
		        	criteria.lt(getField(target.getClass(), e.name, e.trailing_v));   
			        break;
			        
			    case SORT:
			    	
			    	Direction direction=true?Direction.ASC:Direction.DESC;
		   		    query.with(new Sort(direction,e.name));	    	
		            break;
		            
		        case LIMIT:
		        	query.limit(15);
		            break;
			    default:
			      
			        break;
		    }
		}
		
		if(criteria!=null){
	
			query.addCriteria(criteria);
		}
		
	}
	
	
	public <T> T getField(Class t,String name,String value) throws NoSuchFieldException, SecurityException{
		
		Class type = t.getDeclaredField(name).getType();
		
//		System.out.println(t.getDeclaredField("id").getType().getName());
//		System.out.println(t.getDeclaredField("createTime").getType().getName());
//		System.out.println(t.getDeclaredField("fee").getType().getName());
		if(type.equals("class java.lang.String")) 
		{ 			
			return (T) value; 
		}
		else if(type.equals("class java.util.Date")) 
		{ 
			Date date  =  new Date(value);
			
			return (T) date; 
		} 
		else if(type.equals("class java.lang.Boolean")) 
		{ 
			Boolean bool=true; 
			
			if(value.equals("false")) 
			{ 
				bool=false; 
			} 
			return (T) bool; 
		} 
		
		
		else if(type.getName().equals("int")||type.equals("class java.lang.Integer")) 
		{
			
			return (T) new Integer(value);
			
		} 
		
		else if(type.getName().equals("float")||type.equals("class java.lang.Float")) 
		{
			
			return (T) new Float(value);
			
		} 

		else if(type.getName().equals("long")||type.equals("class java.lang.Long")) 
		{ 
			
			return (T) new Long(value);
			
			
		} else if(type.getName().equals("double")||type.equals("class java.lang.Double")) 
		{ 
			
			return (T) new Long(value);
			
		} 
		
		return (T) value;
	}
}
