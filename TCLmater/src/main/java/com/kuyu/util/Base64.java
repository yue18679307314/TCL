package com.kuyu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64 转码解码
 * 
 */
public class Base64 {
	//记录日志
	private static Logger log = LoggerFactory.getLogger(Base64.class);
	private static BASE64Decoder decoder = new BASE64Decoder();

	public static String encode(String s) {
		if (s == null)
			return null;
		return (new BASE64Encoder()).encode(s.getBytes());
	}

	public static String decode(String s) {
		if (s == null)
			return null;
		try {
			return new String(decoder.decodeBuffer(s));
		} catch (Exception e) {
			log.error("decode error", e);
		}
		return null;
	}
   
} 
