package com.kuyu.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


public class SSORestful {
	
	
	public static String  appcode;
	
	public static String  appkey;
	
	public static String  host;
	/**
	 * Encode password.
	 * 
	 * @param plainPassword the plain password
	 * @param salt the salt
	 * @return the string
	 */
	private static String encodePassword(String plainPassword, String salt) {
		MessageDigestPasswordEncoder encoder = null;
		encoder = new ShaPasswordEncoder(256);
		return encoder.encodePassword(plainPassword, salt);
	}
	
	private static JSONObject getAuthenticateJsonObj(String username, String password) {
    	JSONObject obj = new JSONObject();
    	try {
			obj.put("username", username);
			obj.put("password", password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return obj;
    	
    }
	
	private static JSONObject getTokenJsonObj(String ticketName, String ticketValue) {
    	JSONObject obj = new JSONObject();
    	try {
			obj.put("ticketName", ticketName);
			obj.put("ticketValue", ticketValue);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return obj;
    }
	
	public static void getSerivceTicket(String ticket, String service) {
		String httpUrl = host+"/siam/v1/tickets/";
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod method = new PostMethod(httpUrl+ticket);
			method.setRequestHeader(new Header("Content-Type","text/html"));
			method.setRequestHeader(new Header("Accept","text/html, application/xhtml+xml, */*"));
			method.setRequestHeader(new Header("Accept-Language","zh-CN"));
			method.setRequestHeader(new Header("User-Agent","Mozilla/5.0 (Linux; U; Windows NT 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 "));
			method.setRequestHeader(new Header("UA-CPU","AMD64"));
			method.setRequestHeader(new Header("Accept-Encoding","gzip, deflate"));
			method.setRequestHeader(new Header("Connection","Keep-Alive"));
			method.setRequestHeader(new Header("Cookie","SIAMTGT="+ticket+";Domain=.tcl.com;Path=/"));
			JSONObject obj = new JSONObject();
	    	try {
				obj.put("service", service);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setProperty ("jsse.enableSNIExtension", "false");
			RequestEntity requestEntity = new StringRequestEntity("service="+service, "application/json", "utf-8");
			method.setRequestEntity(requestEntity);
			method.releaseConnection();
			httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			System.out.println("getSerivceTicket response：" + response);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证访问Token接口
	 */
	public static void validateToken() {
		String httpUrl = host+"/siam/rest/validate";
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod method = new PostMethod(httpUrl);
			System.setProperty ("jsse.enableSNIExtension", "false");
			//header
			method.setRequestHeader(new Header("Content-Type","application/json"));
			//接入应用授权代码，LDAP设置后提供给应用端调用
			method.setRequestHeader(new Header("appcode",appcode));
			//16位随机字符串（字母和数字）
			method.setRequestHeader(new Header("appkey",appkey));
			//当前时间戳，如：20150403152718Z
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
			String time = sdf.format(new Date());
			method.setRequestHeader(new Header("timestamp",time));
			//appcode+appkey+timestamp加密后值，testsalt与应用授权用户的属性值smart-privatekey值一致
			String encodekey = encodePassword(appcode+appkey+time, appkey);
			method.setRequestHeader(new Header("encode",encodekey));
			//设置需要修改的数据
			String authSSOUserValue = authenticateSSOUser("scmadmin","pass@123");
			JSONObject userJsonObj = new JSONObject(authSSOUserValue);
			JSONObject ticketJsonObj = new JSONObject(userJsonObj.getString("ticketEntry"));
			JSONObject jsonobj = getTokenJsonObj(ticketJsonObj.get("ticketName").toString(), ticketJsonObj.get("ticketValue").toString());
//			JSONObject jsonobj = getTokenJsonObj("HQLtpaToken", "AAECAzU3RDhEMTFENTdEOTc5RERsaW56aXh1YW7rbk0X+YFCrBDTzjEjqIlcNSoO2Q==");
			RequestEntity requestEntity = new StringRequestEntity(jsonobj.toString(), "application/json", "utf-8");
			method.setRequestEntity(requestEntity);
			method.releaseConnection();
			httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			System.out.println("validateToken response：" + response);
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
	/**
	 * 用户认证接口
	 */
	public static String authenticateSSOUser(String username,String password) {
		System.out.println("===========authenticateSSOUser===========");
		String httpUrl = host+"/siam/rest/authenticate";
		try {
			HttpClient httpClient = new HttpClient();  
			PostMethod method = new PostMethod(httpUrl);
			System.setProperty ("jsse.enableSNIExtension", "false");
			//header
			method.setRequestHeader(new Header("Content-Type","application/json"));
			//接入应用授权代码，LDAP设置后提供给应用端调用
			method.setRequestHeader(new Header("appcode",appcode));
			//16位随机字符串（字母和数字）
			method.setRequestHeader(new Header("appkey",appkey));
			//当前时间戳，如：20150403152718Z
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
			String time = sdf.format(new Date());
			method.setRequestHeader(new Header("timestamp",time));
			//appcode+appkey+timestamp加密后值，testsalt与应用授权用户的属性值smart-privatekey值一致
			String encodekey = encodePassword(appcode+appkey+time, appkey);
			method.setRequestHeader(new Header("encode",encodekey));
			//设置需要修改的数据"1", "Abcd1234"/
			JSONObject jsonobj = getAuthenticateJsonObj(username,password);
			RequestEntity requestEntity = new StringRequestEntity(jsonobj.toString(), "application/json", "utf-8");
			method.setRequestEntity(requestEntity);   
			method.releaseConnection();
			httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			System.out.println("authenticateSSOUser response：" + response);
			return response;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getHQLtpaToken(String kv)throws Exception{
		System.out.println("===========getHQLtpaToken===========");
		HttpClient httpClient = new HttpClient();
		String httpUrl = host+"/siam/login?service=http://ep.tcl.com";
		System.setProperty ("jsse.enableSNIExtension", "false");
		GetMethod method = new GetMethod(httpUrl);
		method.setFollowRedirects(false);
		method.setRequestHeader(new Header("Content-Type","text/html"));
		method.setRequestHeader(new Header("Accept","text/html, application/xhtml+xml, */*"));
		method.setRequestHeader(new Header("Accept-Language","zh-CN"));
		method.setRequestHeader(new Header("User-Agent","Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 "));
		method.setRequestHeader(new Header("UA-CPU","AMD64"));
		method.setRequestHeader(new Header("Accept-Encoding","gzip, deflate"));
		method.setRequestHeader(new Header("Connection","Keep-Alive"));
		method.setRequestHeader(new Header("Cookie",kv+";Domain=.tcl.com;Path=/"));
		httpClient.executeMethod(method);
		String response = method.getResponseBodyAsString();
		String respCookie = method.getResponseHeader("Set-Cookie").getValue();
		String respTicket ="";
		if(method.getResponseHeader("Location") != null)
			respTicket = method.getResponseHeader("Location").getValue();
		String HQLtpaToken = "";
		if(respCookie != null && respCookie.length() > 0){
			String []cookies = respCookie.split(";");
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].startsWith("HQLtpaToken")){
					HQLtpaToken = cookies[i].substring("HQLtpaToken".length()+1);
					break;
				}
			}
		}
		String ticket = "";
		if(respTicket != null && respTicket.length() > 0){
			if(respTicket.indexOf("?") > 0){
				ticket = respTicket.substring(respTicket.indexOf("?") +1);
			}
		}
		if(response.indexOf("/siam/login") > 0){
			System.out.println("----------55555555555555555--------------");
		}
		System.out.println("HQLtpaToken="+HQLtpaToken);
		System.out.println("ticket="+ticket);
		System.out.println("authenticateSSOUser response：" + response);
		return HQLtpaToken;
	}
	
	public static void openHttpView()throws Exception{
		System.out.println("===========openHttpView===========");
		HttpClient httpClient  = new HttpClient(); 
		String httpUrl = "http://ep.tcl.com";
		String authSSOUserValue = authenticateSSOUser("scmadmin","pass@123");
		JSONObject userJsonObj = new JSONObject(authSSOUserValue);
		JSONObject ticketJsonObj = new JSONObject(userJsonObj.getString("ticketEntry"));
		String HQLtpaToken = getHQLtpaToken(getKV(ticketJsonObj));
		System.setProperty ("jsse.enableSNIExtension", "false");
		GetMethod method = new GetMethod(httpUrl);
		method.setRequestHeader(new Header("Content-Type","text/html"));
		method.setRequestHeader(new Header("Accept","text/html, application/xhtml+xml, */*"));
		method.setRequestHeader(new Header("Accept-Language","zh-CN"));
		method.setRequestHeader(new Header("User-Agent","Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 "));
		method.setRequestHeader(new Header("UA-CPU","AMD64"));
		method.setRequestHeader(new Header("Accept-Encoding","deflate"));
		method.setRequestHeader(new Header("Connection","Keep-Alive"));
		method.setRequestHeader(new Header("Cookie",getKV(ticketJsonObj)+";HQLtpaToken="+HQLtpaToken+";Domain=.tcl.com;Path=/"));
		httpClient.executeMethod(method);
		String response = method.getResponseBodyAsString();
		System.out.println("authenticateSSOUser response：" + response);
		//openPortal(getKV(ticketJsonObj), HQLtpaToken);
	}
	
	private static void openPortal(String kv, String HQLtpaToken)throws Exception{
		System.out.println("===========openPortal===========");
		HttpClient httpClient  = new HttpClient(); 
		String httpUrl = "http://ep.tcl.com";
		GetMethod method = new GetMethod(httpUrl);
		method.setRequestHeader(new Header("Content-Type","text/html"));
		method.setRequestHeader(new Header("Accept","text/html, application/xhtml+xml, */*"));
		method.setRequestHeader(new Header("Accept-Language","zh-CN"));
		method.setRequestHeader(new Header("User-Agent","Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 "));
		method.setRequestHeader(new Header("UA-CPU","AMD64"));
		method.setRequestHeader(new Header("Accept-Encoding","gzip, deflate"));
		method.setRequestHeader(new Header("Connection","Keep-Alive"));
		method.setRequestHeader(new Header("Cookie",kv+";HQLtpaToken="+HQLtpaToken+";Domain=.tcl.com;Path=/"));
		httpClient.executeMethod(method);
		String response = method.getResponseBodyAsString();
		System.out.println("authenticateSSOUser response：" + response);
	}
	private static String getKV(JSONObject ticketJsonObj) throws JSONException{
		return ticketJsonObj.getString("ticketName")+"="+ticketJsonObj.getString("ticketValue");
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//String str = "";
		//System.out.println(new String(str.getBytes("GBK")));
		//System.out.println("authenticateSSOUser response：" + URLDecoder.decode("%E4%BB%BB%E5%8A%A1%E7%AE%A1%E7%90%86", "UTF-8"));
		//openHttpView();
		//getHQLtpaToken();
//		authenticateSSOUser("scmadmindd","pass@123");
//		SSORestful.host = "http://ssosit.tcl.com/";
//		SSORestful.appcode="jr_oa_app";
//		SSORestful.appkey="A5LoJ6KeS6ZiA5Yo";
		SSORestful.host = "https://login.tcl.com";
		SSORestful.appcode="tmt_sctgf";
		SSORestful.appkey="YWTqa3NmOGYhYOAk";
//		String authSSOUserValue = authenticateSSOUser("chenpiao","chenpu5201314@");
		String authSSOUserValue = authenticateSSOUser("zhukaiwei","hao!@3.com");
		net.sf.json.JSONObject userJsonObj = net.sf.json.JSONObject.fromObject(authSSOUserValue);
		String status = userJsonObj.getString("status").toString();
		System.out.println("true".equals(status));
		//JSONObject ticketJsonObj = new JSONObject(userJsonObj.getString("ticketEntry"));
		
		//validateToken();//ticketJsonObj.getString("ticketValue")
		//getSerivceTicket("TGT-73-qQu6fOwAy3yZi30hpdHFSmG6bbOKuGiQnWdhgdYcmm0jrdPUIY-SIAM",host+"/siam/console.do");
	}
}
