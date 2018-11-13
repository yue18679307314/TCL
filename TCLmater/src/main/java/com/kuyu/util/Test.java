package com.kuyu.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

	
	public static void main(String[] args) {
		
//		String token = "kuyuwork";
//		String signature = "0f122640e36d36ac2784d7e2a776da0f213f14e1";
//		Timestamp tt = new Timestamp(new Date().getTime());
//		String timestamp = "1495613244";
//		String nonce = "cziYrJ";
//		String temp = "UVKlWHBG";
//		
//		String [] tempArr = new String[]{token,timestamp,nonce};
//		
//		Arrays.sort(tempArr);
//		String finalstr = "";
//		for(String str:tempArr){
//			finalstr = finalstr+str;
//		}
//		String c_signature = SHA1(finalstr);
//		System.out.println(finalstr);
//		System.out.println(c_signature);
//		tt();
//		httpgettest();
		httpposttest();
	}
	
	public static void httpgettest(){
		String url = "http://10.126.124.29:7001/temppro/importBusiness.do?interfaceNo=0&requestParams={\"REQUEST_DEPART\":\"90001279\",\"MEMO\":\"临促新增银行账号20171017\",\"DETAIL_LIST\":[{\"ACCOUNT_VALUE\":\"4521468713465204549\",\"OPEN_PROVINCE\":\"广东省\",\"ACCOUNT_BANK_NAME\":\"中国银行\",\"OPEN_BRANCH\":\"西丽支行\",\"ACCOUNT_NAME\":\"开伟\",\"OPEN_CITY\":\"深圳\"}],\"REQUEST_USER\":\"00024518\"}";
		String url2 = "http://10.126.124.29:7001/temppro/importBusiness.do?interfaceNo=0&requestParamffs=";
		try {
			String  result  = HttpUtils.get(url2);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void httpposttest(){
		String url = "http://10.126.124.29:7001/temppro/importBusiness.do?interfaceNo=0&requestParams={\"REQUEST_DEPART\":\"90001279\",\"MEMO\":\"临促新增银行账号20171017\",\"DETAIL_LIST\":[{\"ACCOUNT_VALUE\":\"4521468713465204549\",\"OPEN_PROVINCE\":\"广东省\",\"ACCOUNT_BANK_NAME\":\"中国银行\",\"OPEN_BRANCH\":\"西丽支行\",\"ACCOUNT_NAME\":\"开伟\",\"OPEN_CITY\":\"深圳\"}],\"REQUEST_USER\":\"00024518\"}";
		String url2 = "http://10.126.124.29:7001/temppro/importBusiness.do";
		String content = "{\"REQUEST_DEPART\":\"90001279\",\"MEMO\":\"临促新增银行账号20171017\",\"DETAIL_LIST\":[{\"ACCOUNT_VALUE\":\"4521468713465204549\",\"OPEN_PROVINCE\":\"广东省\",\"ACCOUNT_BANK_NAME\":\"中国银行\",\"OPEN_BRANCH\":\"西丽支行\",\"ACCOUNT_NAME\":\"开伟\",\"OPEN_CITY\":\"深圳\"}],\"REQUEST_USER\":\"00024518\"}";
		String param = "interfaceNo=0&requestParams={\"REQUEST_DEPART\":\"90001279\",\"MEMO\":\"临促新增银行账号20171017\",\"DETAIL_LIST\":[{\"ACCOUNT_VALUE\":\"4521468713465204549\",\"OPEN_PROVINCE\":\"广东省\",\"ACCOUNT_BANK_NAME\":\"中国银行\",\"OPEN_BRANCH\":\"西丽支行\",\"ACCOUNT_NAME\":\"开伟\",\"OPEN_CITY\":\"深圳\"}],\"REQUEST_USER\":\"00024518\"}";
		Map<String, String> params = new HashMap<>();
		params.put("requestParams", content);
		params.put("interfaceNo", "0");
		//			String  result  = HttpUtils.post(url2, content);
//					try {
//						String result = HttpUtils.post(url2, params);
//						System.out.println(result);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					String result = HttpRequest.sendGet(url2, param);
					System.out.println("result====="+result);
	}
	
	public static void tt(){
		String[] str = {"nba", "abc", "cba", "zz", "qq", "haha"};
		Arrays.sort(str);
		String finalstr = "";
		for(String strs:str){
			finalstr = finalstr+strs;
		}
		System.out.println(Arrays.toString(str));
		System.out.println(finalstr);
		
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	
}
