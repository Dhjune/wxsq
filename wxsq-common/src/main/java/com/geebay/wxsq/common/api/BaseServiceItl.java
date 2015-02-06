package com.geebay.wxsq.common.api;

import java.util.List;

/**
 * service层访问 ,接口类
 *@author dhjune
 */
public interface BaseServiceItl<T> {

	public T get(String id) throws Exception;

    public void save(T obj)throws Exception;

    public void delete(T obj)throws Exception;

    public void update(T obj) throws Exception;
    

}
