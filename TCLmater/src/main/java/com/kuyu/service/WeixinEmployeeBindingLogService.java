package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.WeixinEmployeeBinding;
import com.kuyu.model.WeixinEmployeeBindingLog;


/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description
 */
public interface WeixinEmployeeBindingLogService extends IService<WeixinEmployeeBindingLog> {

	public void insertWeixinEmployeeBindingLog(WeixinEmployeeBindingLog weixinEmployeeBindingLog);

}
