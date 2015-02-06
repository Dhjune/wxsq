package com.geebay.wxsq.common.api;

import java.util.List;

/**
 * dao层访问 ,接口类
 *@author dhjune
 */
public interface  BaseDaoItl<T> {

    public T get(int id) throws Exception;    
    public void save(T obj) throws Exception;
    public void update(T obj) throws Exception;
    public void delete(T obj) throws Exception;

}
