package com.geebay.wxsq.common.trans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class transactionProcess {
	
	
	@Pointcut(value="@annotation(trans)")  
    public void transactionAspect(Transactional trans) {  
		
    }
	
	
	@Around(value = "transactionAspect(trans)",  argNames="trans")
	public void execTransaction(ProceedingJoinPoint pjp,Transactional trans){
		 Object[] args = pjp.getArgs();
		 try {
			pjp.proceed(args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startTransaction(){
		
	}
	
	
	
	@Before(value = "transactionAspect(trans)",  argNames="trans")  
	public void execBefore(JoinPoint point, Transactional trans){
				
		// 调用方法的参数
        Object[] args = point.getArgs();
        String sId = null;
        String dId = null;
        String sName = null;
        String dName = null;
        Map<String,Object> values = null;
       
        try {
        	
        	Method method =  args[0].getClass().getMethod("getId", new Class[0]);
        	sId = (String) method.invoke(args[0], new Object[0]);
        	method =  args[0].getClass().getMethod("getCollection", new Class[0]);
        	sName = (String) method.invoke(args[0], new Object[0]);
        	
        	method =  args[1].getClass().getMethod("getId", new Class[0]);
        	dId = (String) method.invoke(args[1], new Object[0]);
        	method =  args[1].getClass().getMethod("getCollection", new Class[0]);
        	dName = (String) method.invoke(args[1], new Object[0]);
        	
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        /*
        // 调用的方法名
        String methodName = point.getSignature().getName();
        // 获取目标对象(形如：com.action.admin.LoginAction@1a2467a)
        Object target = point.getTarget();
        Method method = null;
        try {
        	method = target.getClass().getMethod(methodName, Object.class);
        	 method.invoke(target, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       */	
	}
	
	@After(value = "transactionAspect(trans)",  argNames="trans")  
	public void  execAfter(JoinPoint point, Transactional trans){
		
	}
	
}
