package com.kuyu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kuyu.common.CommonConstants;
import com.kuyu.service.PropertiesConfigService;

import net.sf.json.JSONObject;

@Component
public class SendWeiXinTemplateMSGUtil {
	
	private Logger log = LoggerFactory.getLogger(SendWeiXinTemplateMSGUtil.class);
	
	@Autowired
	private PropertiesConfigService propertiesConfigService;
	
	public boolean sendMSG(String templateId,String toUserOpenid, String firstStr,String[] args,String endStr){
		
		
		
		JSONObject data = new JSONObject();
		JSONObject valObj = new JSONObject();
		valObj.put("value", firstStr);
//		valObj.put("color", "#FF0000");
		data.put("first", valObj);
		int index = 1;
		for(String arg:args){
			valObj.put("value", arg);
			valObj.put("color", "#173177");
			data.put("keyword"+index, valObj);
			index++;
		}
		valObj.put("value", endStr);
		data.put("remark", valObj);
		

		String respMsg = "";
	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", toUserOpenid);
			jsonObject.put("template_id", templateId);
//			jsonObject.put("url", "http://www.tcl.com");
//			jsonObject.put("url", "");
//			jsonObject.put("topcolor", "#FF0000"); // 顶部颜色：红
			// 针对新增模板，新增两个参数
			jsonObject.put("data", data);
			String content = jsonObject.toString();
			log.info("send msg===>>"+content);
			// 发送消息[最多尝试3次]
			Throwable throwable = null;
			String accessToken = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_ACCESSTOKEN).getPvalue();
			// 构建连接URL
			String url = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_SEND_TEMPLATE_MSGURL).getPvalue() + accessToken;
			// 发送消息
			log.info("send template URL "+url);
			log.info("send template message "+content);
			try {
				respMsg = HttpUtils.post(url, content);
				log.info("response message ==="+respMsg);
			} catch (Throwable cause) {
				throwable = cause;
				log.error(" Send message of template exception ==" + throwable.getMessage(), throwable);
				return false;
			}

		return true;
	}
	
	
	

}
