package com.kuyu.controller;

import java.text.SimpleDateFormat;  
import java.util.Date;

import com.kuyu.annotation.AOP_Controller_LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.kuyu.common.DataCache;  
/** 
 * 是Spring Boot项目的核心注解,主要是开启自动配置 
 */  
@AOP_Controller_LOG
// 开启缓存  
public class CacheTestController {

	    @Autowired
	    private DataCache dataCache;  
	      
	    @RequestMapping("/put")  
	    public String put(String id, String value) {  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        return  sdf.format(new Date()) + " : value is " + dataCache.put(id, value) ;  
	    }  
	  
	    @RequestMapping("/get")  
	    public String query(String id){  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        return sdf.format(new Date()) + " : value is " +dataCache.query(id) ;
	    }  
	      
	    @RequestMapping("/remove")  
	    public String remove(String id) {  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        dataCache.remove(id) ;  
	        return sdf.format(new Date()) + " : success " ;  
	    }  
	      
	
}
