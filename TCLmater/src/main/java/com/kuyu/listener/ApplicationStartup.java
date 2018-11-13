package com.kuyu.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.kuyu.service.PropertiesConfigService;
import com.kuyu.task.AccessTokenService;
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	private PropertiesConfigService propertiesConfigService;
	
	private AccessTokenService accessTokenService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		propertiesConfigService = (PropertiesConfigService)event.getApplicationContext().getBean(PropertiesConfigService.class);
		accessTokenService = (AccessTokenService)event.getApplicationContext().getBean(AccessTokenService.class);

		accessTokenService.getWxAccessToken(propertiesConfigService);
		accessTokenService.getWxJSSDKTicket(propertiesConfigService);
	}
}