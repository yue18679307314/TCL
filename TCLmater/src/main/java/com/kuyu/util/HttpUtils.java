package com.kuyu.util;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class HttpUtils {
	protected static final Logger log = Logger.getLogger(HttpUtils.class);
	
	@Value("${httpclient.cacheValue}")
	private static String cacheValue;
	// Http get请求
	public static String get(String url) throws IOException {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.getParams().setContentCharset("utf-8");
		long time = System.currentTimeMillis();
		log.info("httpclient <get> request start time:"+time);
		String result = excuteHttpMethod(client,method);
		log.info("httpclient <get> request url:"+url+", end time:"+(System.currentTimeMillis() - time));
		return result;
	}

	// 释放连接
	public static void close(HttpMethod httpMethod) {
		try {
			httpMethod.releaseConnection();
		} catch (Throwable cause) {

		}
	}

	public static  String excuteHttpMethod(HttpClient client,HttpMethod method)throws IOException{
		String respMessage = "";
		boolean isSent = false;
		IOException cause = null;
		for (int i = 0; i < 3; i++) {
			try {
				int cacheTimeout =0;
				if(StringUtils.isNotBlank(cacheValue)){
				    cacheTimeout = Integer.parseInt(cacheValue); //毫秒
					if(cacheTimeout > 0){
						// 设置连接超时时间(单位毫秒) 
						client.getHttpConnectionManager().getParams().setConnectionTimeout(cacheTimeout);//连接超时时间	
					}else{
						// 设置连接超时时间(单位毫秒)  默认为5000毫秒
						client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);//连接超时时间	
					}
				}else{
					// 设置连接超时时间(单位毫秒)  默认为5000毫秒
					client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);//连接超时时间	
				}
				if(StringUtils.isNotBlank(cacheValue)){
				    cacheTimeout = Integer.parseInt(cacheValue); //毫秒
					if(cacheTimeout > 0){	
						// 设置读数据超时时间(单位毫秒) 
						client.getHttpConnectionManager().getParams().setSoTimeout(cacheTimeout);
					}else{
						// 设置读数据超时时间(单位毫秒)  默认30毫秒
						client.getHttpConnectionManager().getParams().setSoTimeout(5000);
					}
				}else{
					// 设置读数据超时时间(单位毫秒)  默认30毫秒
					client.getHttpConnectionManager().getParams().setSoTimeout(5000);
				}
				int status = client.executeMethod(method);
				if (HttpStatus.SC_OK == status) {
					respMessage = method.getResponseBodyAsString();
					isSent = true;
					break;
				}
			} catch (IOException e) {
				cause = e;
				respMessage = "";
			}
		}
		close(method);
		if (!isSent) {
			throw new IOException(cause);
		}
		return respMessage;
	}
	
	
	// http Post请求
	public static String post(String url, Map<String, String> paramsMap) throws IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setContentCharset("utf-8");
		Iterator<String> it = paramsMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			method.addParameter(key, paramsMap.get(key));
		}
		long time = System.currentTimeMillis();
		log.info("httpclient <post> request start time:"+time);
		String result = excuteHttpMethod(client,method);
		log.info("httpclient <post> request url:"+url+",paramsMap:"+paramsMap+", end time:"+(System.currentTimeMillis() - time));
		return result;
	}

	// http Post请求
	@SuppressWarnings("deprecation")
	public static String post(String url, String content) throws IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setContentCharset("utf-8");
		method.addRequestHeader("Content-Type", "application/json");
		method.setRequestBody(content);
		return excuteHttpMethod(client,method);
	}


}
