package com.kuyu.service;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.WeixinUserInfo;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description
 */
public interface WeixinUserInfoService extends IService<WeixinUserInfo> {

	WeixinUserInfo selectBykey(@Param("openid") String openid);
	void insertTpmUserWxInfo(WeixinUserInfo weixinUserInfo);
}
