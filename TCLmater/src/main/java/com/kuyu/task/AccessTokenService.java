package com.kuyu.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kuyu.common.CommonConstants;
import com.kuyu.service.PropertiesConfigService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.HttpUtils;

import net.sf.json.JSONObject;
@Component
public class AccessTokenService {
	
	private static final Logger log = LoggerFactory.getLogger(AccessTokenService.class);
	
	@Autowired
	private PropertiesConfigService propertiesService;
	
	public String getWxAccessToken(PropertiesConfigService propertiesConfigService) {
		String accesstoken = "";
		try {
			String appId =propertiesConfigService.selectBykey(CommonConstants.WEIXIN_APPID).getPvalue();
			String secret = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_APPSECRET).getPvalue();
//			String auth_url = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_AUTH2_GET_TOKEN_URL).getPvalue();
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ appId + "&secret=" + secret;
			log.info("WeChatAccessTokenJob.getWxAccessToken() send to web start:url="
					+ url);
			String result =  HttpUtils.get(url);
			JSONObject jsonObj = JSONObject.fromObject(result); // 发送get请求获取json对象
			log.info("WeChatAccessTokenJob.getWxAccessToken() send to web end:url="
					+ url + " result = " +result);
			accesstoken = jsonObj.getString("access_token");
			propertiesConfigService.updateBykey(accesstoken, CommonConstants.WEIXIN_ACCESSTOKEN);
			propertiesConfigService.updateBykey(DateUtils.getLongDateStr(), CommonConstants.ACCESS_TOKEN_UPDATE_TIME);
		} catch (Exception e) {
			log.info("WeChatAccessTokenJob.getWxAccessToken() error"
					+ e.getMessage() + "\n" + e.getCause());
		}
		return accesstoken;
	}
	
	public String getWxJSSDKTicket(PropertiesConfigService propertiesConfigService) {
		String ticket = "";
		try {
			String accessToken =propertiesConfigService.selectBykey(CommonConstants.WEIXIN_ACCESSTOKEN).getPvalue();
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ accessToken + "&type=jsapi";
			log.info("WeChatAccessTokenJob.getWxJSSDKTicket() send to web start:url="
					+ url);
			String result =  HttpUtils.get(url);
			JSONObject jsonObj = JSONObject.fromObject(result); // 发送get请求获取json对象
			log.info("WeChatAccessTokenJob.getWxJSSDKTicket() send to web end:url="
					+ url + " result = " +result);
			ticket = jsonObj.getString("ticket");
			propertiesConfigService.updateBykey(ticket, CommonConstants.WEIXIN_JSSDKTICKET);
		} catch (Exception e) {
			log.info("WeChatAccessTokenJob.getWxJSSDKTicket() error"
					+ e.getMessage() + "\n" + e.getCause());
		}
		return ticket;
	}

    /**
     * 导入借款单任务
     * 每隔一小时执行一次执行一次
     */
    @Scheduled(cron = "${cron.updateAccessTokenTime}")//周一到周五每晚22:00执行一次
    public void updateAccessToken() {
        log.info("*****正在获取accesstoken,执行时间:{}*****", DateUtils.currentTime());
        this.getWxAccessToken(propertiesService);
        this.getWxJSSDKTicket(propertiesService);
    }

	
}
