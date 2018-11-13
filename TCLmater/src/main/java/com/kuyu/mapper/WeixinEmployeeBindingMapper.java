package com.kuyu.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.WeixinEmployeeBinding;

public interface WeixinEmployeeBindingMapper extends BaseMapper<WeixinEmployeeBinding>{
	
	public WeixinEmployeeBinding selectByCode(@Param("person_code") String person_code);
	
	public WeixinEmployeeBinding selectByOpenid(@Param("openId") String openId);
	
	public void deleteByPersonCode(@Param("person_code") String person_code);

}