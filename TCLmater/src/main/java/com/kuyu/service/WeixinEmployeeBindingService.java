package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.WeixinEmployeeBinding;


/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description
 */
public interface WeixinEmployeeBindingService extends IService<WeixinEmployeeBinding> {

	public void insertWeixinEmployeeBinding(WeixinEmployeeBinding weixinEmployeeBinding);
	
	public boolean deleteByPersonCode(String person_code);
	
	public WeixinEmployeeBinding selectByCode(String person_code);
	
	public WeixinEmployeeBinding selectByOpenid(String openId);
}
