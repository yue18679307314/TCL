package com.kuyu.weixin;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kuyu.common.CommonConstants;
import com.kuyu.model.WeixinUserInfo;
import com.kuyu.service.PropertiesConfigService;
import com.kuyu.service.WeixinUserInfoService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.EmojiUtil;
import com.kuyu.util.HttpUtils;

//渠道事件的分发
@Component
public class ChannelReqDispatcher {


	protected static final int SCOPE_REQUEST = 0;
	protected static final int SCOPE_SESSION = 1;
	
	@Autowired
	private PropertiesConfigService propertiesConfigService;
	
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;
	
	public ChannelRespMessage dispatch(ChannelReqMessage channelReqMsg,HttpServletRequest req) throws Throwable {
		// 处理关注事件
		if (CommonConstants.MSGTYPE_EVENT.equalsIgnoreCase(channelReqMsg.getMsgType())) {
			return this.event(channelReqMsg,req);
		}
		
		// 微信出现新的MSG状态,直接返回空,控制层会返回帮助信息.
		return null;
	}
	

	/**
	 * 事件处理
	 * 
	 * @param channelReqMsg
	 * @param ChannelRespMessage
	 * @throws Throwable
	 */
	private ChannelRespMessage event(ChannelReqMessage channelReqMsg,HttpServletRequest req) throws Throwable {
		// 订阅(用户关注公众账户)
		if (CommonConstants.EVENT_SUBSCRIBE.equalsIgnoreCase(channelReqMsg.getEvent())) {
			WeixinUserInfo userInfo = this.getUserBaseInfo(channelReqMsg.getFromUserName(), channelReqMsg.getToUserName());
			if(userInfo!=null){
				WeixinUserInfo weixinUserInfo = weixinUserInfoService.selectBykey(userInfo.getOpenId());
				if(weixinUserInfo==null){
					weixinUserInfoService.insertTpmUserWxInfo(userInfo);
				}
			}
//			log.info("EVENT_SUBSCRIBE,getUserBaseInfo=" + (userInfo == null ? "null." : userInfo.getUserId() ));
//			if (userInfo == null) {
//				return null;
//			}
			return null;
		}
		
		return null;
	}
	
	// 获取用户基本信息
	public WeixinUserInfo getUserBaseInfo(String userId, String publicId) throws Throwable {
		WeixinUserInfo userInfo = null;
		String url = propertiesConfigService.selectBykey(CommonConstants.SUBSCRIBE_GET_WEIXIN_USER_INFO_URL).getPvalue();
		url += "?access_token=" + propertiesConfigService.selectBykey(CommonConstants.WEIXIN_ACCESSTOKEN).getPvalue() + "&openid=" + userId;
		String respMsg = HttpUtils.get(url);
		if (StringUtils.isNotEmpty(respMsg)) {
			JSONObject objResult = JSONObject.fromObject(respMsg);
			if (!objResult.containsKey("errcode")) {
				userInfo = new WeixinUserInfo();
				String sub = objResult.optString("subscribe");
				userInfo.setOpenId(userId);// 用户id
				String source = EmojiUtil.filterEmoji(objResult.optString("nickname"));
				userInfo.setNikeName(source);
				userInfo.setSex(objResult.optString("sex"));
				userInfo.setCity(objResult.optString("city"));
				userInfo.setProvince(objResult.optString("province"));
				userInfo.setCountry(objResult.optString("country"));
				userInfo.setHeadImgUrl(objResult.optString("headimgurl"));
				userInfo.setRemark(objResult.optString("remark"));
				userInfo.setGroupid(objResult.optString("groupid"));
				if("1".equals(sub)){
					userInfo.setSubscribeTime(DateUtils.getTimeStampStr(new Date(Long.valueOf(objResult.getString("subscribe_time"))*1000)));
				}
			}
		}
		return userInfo;
	}
	


}
