package com.kuyu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.PropertiesConfig;
import org.apache.ibatis.annotations.Param;

public interface PropertiesConfigMapper extends BaseMapper<PropertiesConfig>{

	PropertiesConfig selectBykey(@Param("key") String key);
	
	public boolean updateBykey(@Param("value") String value,@Param("key") String key);
}