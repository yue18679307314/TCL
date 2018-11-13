package com.kuyu.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class DegistUtils {

	//消息摘要的验签
	//
	public static boolean verify(String orgDigest, String algorithm, List<String> contents, boolean isSorted) 
			throws Exception {
		boolean isVerified = false;
		MessageDigest digestor = MessageDigest.getInstance(algorithm);
		String orginMsg = getMessage(contents, isSorted);
		byte[] digestBytes = digestor.digest(orginMsg.getBytes());
		StringBuffer digestBuffer = new StringBuffer();
		for (int n = 0; n < digestBytes.length; n++) {
		 String	 alphabet = (java.lang.Integer.toHexString(digestBytes[n] & 0xff));
			if (alphabet.length() == 1)
				digestBuffer.append("0").append(alphabet);
			else
				digestBuffer.append(alphabet);
		}
		String digestMsg = digestBuffer.toString();
		if (digestMsg.equals(orgDigest)) {
			isVerified = true;
		}

		return isVerified;
	}

	// 对消息进行过滤排序
	static String getMessage(List<String> content, boolean isSorted) {
		List<String> attrList = new ArrayList<String>();
		 Iterator<String> it = content.iterator();
		while (it.hasNext()) {
		   String  propValue = it.next();
			if (StringUtils.isNotEmpty(propValue)) {
				attrList.add(propValue);
			}
		}
		// 排序字符串
		if (isSorted) {
			Collections.sort(attrList); // 字典排序
		}
		StringBuffer strsBuffer = new StringBuffer();
		for (String propValue : attrList) {
			strsBuffer.append(propValue);
		}
		return strsBuffer.toString();
	}

	//消息摘要
	public static String digest(String content,String algorithm) throws NoSuchAlgorithmException{
		if(StringUtils.isEmpty(algorithm)){
			algorithm ="MD5";
		}
		MessageDigest digestor = MessageDigest.getInstance(algorithm);
		byte[] digestBytes = digestor.digest(content.getBytes());
		return new String( Base64.encodeBase64( digestBytes) );
	}
	
}
