package com.kuyu.common;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.annotation.PostConstruct;  
  
import org.springframework.cache.annotation.CacheEvict;  
import org.springframework.cache.annotation.CachePut;  
import org.springframework.cache.annotation.Cacheable;  
import org.springframework.stereotype.Component;  
  
@Component  
public class DataCache {  
      
    private Map<String, Object> dataMap = new HashMap<>();  
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
    /** 
     * 初始化 
     */  
    @PostConstruct  
    public void init() {   
    }  
      
    /** 
     * 查询 
     */  
    @Cacheable(value="guava" ,key="#id + 'dataMap'")  
    public Object query(String id) {  
          
        System.out.println(sdf.format(new Date()) + " : query id is " + id);  
        return dataMap.get(id);  
    }  
      
    /** 
     * 插入 或者更新 
     * 插入或更新数据到dataMap中
     */  
    @CachePut(value="guava" ,key="#id + 'dataMap'")  
    public Object put(String id, Object value) {  
        String nowTime = sdf.format(new Date());
        System.out.println(nowTime + " : add data ,id is "+ id);  
        dataMap.put(id, value);  
        // data persistence  
        return value;  
    }
      
    /** 
     * 删除 
     * 删除dataMap里面的数据
     */  
    @CacheEvict(value="guava" , key="#id + 'dataMap'")  
    public void remove(String id) {
        System.out.println(sdf.format(new Date()) + " : remove id is "+ id + " data");  
        dataMap.remove(id);  
        // data remove    
    }  
      
      
}  
