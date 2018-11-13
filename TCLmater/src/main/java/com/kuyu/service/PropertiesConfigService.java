package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.PropertiesConfig;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description
 */
@Service("propertiesConfigService")
public interface PropertiesConfigService extends IService<PropertiesConfig> {

	PropertiesConfig selectBykey(@Param("key") String key);
	
	public boolean updateBykey(String value ,String key) ;

}
