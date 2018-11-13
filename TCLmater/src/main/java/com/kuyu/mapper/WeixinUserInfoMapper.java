package com.kuyu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.WeixinUserInfo;
import org.apache.ibatis.annotations.Param;


public interface WeixinUserInfoMapper extends BaseMapper<WeixinUserInfo>{

	WeixinUserInfo selectByOpenId(@Param("openid") String openid);
	void insertTpmUserWxInfo(WeixinUserInfo weixinUserInfo);
}