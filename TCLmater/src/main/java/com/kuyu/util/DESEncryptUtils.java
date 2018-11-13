package com.kuyu.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 加密解密
 * 
 * @author qinjilin
 * 
 */
public class DESEncryptUtils {

	private static Log log = LogFactory.getLog(DESEncryptUtils.class);
	
	public static String REALPATH_CRYPT_KEY = "gzllrqc/MKHKO(F<>";			// 当没有传入key的时候，默认使用该key

	//private static final String REDIRECT_LOGIN_CRYPT_KEY = "#D!90bn2^9@~l8falop#*[yi&}Lv(hAz";
	
	public static String DES = "DES";
	
	 public static final String AES_KEY = "AES";
	 
	 public static final String BASE_KEY = "][poiuytrewq";//KEY

	private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	private static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}
	
	private static String byte2hex(byte[] b) {

		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 密码解密
	 * 
	 * @param data	需要解密的String
	 * @param key   加密时候所用的key
	 * @return	解密之后的原数据
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					key == null ? REALPATH_CRYPT_KEY.getBytes() : key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String src, String key) {
		try {
			return byte2hex(encrypt(src.getBytes(), 
					key == null ? REALPATH_CRYPT_KEY.getBytes() : key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
//		String str1 ="24000000006700173D0001HCeh4ePAbRoxtP08UxsKTUHoE+/xs4YZEGicEMUc10YpRh/tEA2xQ3dNlmSk1ovIyuOB83nMnF7/brSpeCoZ6mXgmuDuHFXSHfHS+T26jGU=";
//		String key = "qinjilin";
//		String mm = encrypt(string.substring(45),string.substring(34, 8));
//		System.out.println(mm);
//		String res = "http://care.tcl.com/01/24000000006700173D0001/TCL/XUNI/XUNI/XUNI1700001/500/4A-XUNI01-001/08-XUNI01-001/08-XUNI01-002";
//		String str = "OtZoSiBzb+IMzhfC+m6iCpaZJQFd51xHD3+5/qnAVKX0fShgjPNeJYbUEfevvgMu+2kLXWbITZ9wYlZvY0ZblRzG5ZeXa+9Sw1pSheobJvE=";
//		System.out.println(str.length());
//		System.out.println(str1.length());
		//		str = Base64.decode(str);
//		System.out.println(str.substring(0, 45));
//		System.out.println(str.substring(45));
//		System.out.println(str1.substring(34,42));
//		String cc = res.substring(0,45)+aesDecrypt(res.substring(45), str1.substring(34,42));
//		System.out.println(str1.substring(0,45)+aesDecrypt(Base64.decode(str1.substring(45)), str1.substring(34,42)));
//		System.out.println(encrypt(res,"507134G0"));
//		System.out.println(Base64.decode(str1.substring(45)));
//		System.out.println(Base64.encode(encrypt(res,"507134G0")));
//		System.out.println(cc);
		String resultString="24001094411507134G0001OtZoSiBzb+IMzhfC+m6iCpaZJQFd51xHD3+5/qnAVKV8towRfjOvi7RJ0z/Do02W/j9Ar4qWfoilwGwvmlrq780xndv1tHAFRgtGP4ef0kk=";
	    try {
			resultString=resultString.substring(0,22)+DecryptDoNet(resultString.substring(22),resultString.substring(11, 19));
			System.out.println(resultString);
	    } catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	  public static String generateSignForWeixin(String key) {
//	        key = encodeAndAesEncrypt(key);
//	        String content = key.concat("weixin").concat(BASE_KEY);
//	        //将以上字符串加密为BASE64字符串
//	        byte[] baseByte = Base64.encodeBase64(content.getBytes());
//	        //再将base64串转成md5
//	        return DigestUtils.md5Hex(baseByte);
//	    }

	    /**
	     * 利用AES加密key
	     *
	     * @param key
	     * @return 返回加密后的key
	     */
	    public static String encodeAndAesEncrypt(String key) {
	        return parseByte2HexStr(aesEncrypt(key, AES_KEY));
	    }

	    /**
	     * AES解密加密后的key
	     *
	     * @param secretKey 加密的key
	     * @return 返回解密后的key
	     */
	    public static String decodeAndAesDecrypt(String secretKey) {
	        byte[] cardbyte = parseHexStr2Byte(secretKey);
	        return new String(aesDecrypt(cardbyte, AES_KEY));
	    }
	    
	    
	    /**
	    /**
	     * 加密
	     *
	     * @param content  需要加密的内容
	     * @param password 加密密码
	     * @return
	     */
	    public static byte[] aesEncrypt(String content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");

	            // kgen.init(128, new SecureRandom(password.getBytes()));
	            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	            secureRandom.setSeed(password.getBytes());
	            kgen.init(128, secureRandom);

	            SecretKey secretKey = kgen.generateKey();
	            byte[] enCodeFormat = secretKey.getEncoded();
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            byte[] byteContent = content.getBytes("utf-8");
	            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(byteContent);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static byte[] aesDecrypt(String content, String password) {
	    	return aesDecrypt(parseHexStr2Byte(content),password);
	    }
	    
	    /**
	     * 解密
	     *
	     * @param content  待解密内容
	     * @param password 解密密钥
	     * @return
	     */
	    public static byte[] aesDecrypt(byte[] content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");
	            //kgen.init(128, new SecureRandom(password.getBytes()));
	            //防止linux下 随机生成key
	            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	            secureRandom.setSeed(password.getBytes());
	            kgen.init(128, secureRandom);

	            SecretKey secretKey = kgen.generateKey();
	            byte[] enCodeFormat = secretKey.getEncoded();
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(content);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    /**
	    /**
	     * 加密 与C#互通
	     *
	     * @param content  需要加密的内容
	     * @param password 加密密码
	     * @return
	     */
	    public static byte[] aesEncryptForCSharp(String content, String password) {
	        try {
	            byte[] enCodeFormat = password.getBytes("UTF-8");
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            byte[] byteContent = content.getBytes("utf-8");
	            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(byteContent);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();	
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 解密 与C#互通
	     *
	     * @param content  待解密内容
	     * @param password 解密密钥
	     * @return
	     */
	    public static byte[] aesDecryptForCSharp(byte[] content, String password) {
	        try {
	        	byte[] enCodeFormat = password.getBytes("UTF-8");
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(content);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        return null;
	    }

	    /**
	     * 将二进制转换成16进制
	     *
	     * @param buf
	     * @return
	     */
	    public static String parseByte2HexStr(byte buf[]) {
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < buf.length; i++) {
	            String hex = Integer.toHexString(buf[i] & 0xFF);
	            if (hex.length() == 1) {
	                hex = '0' + hex;
	            }
	            sb.append(hex.toUpperCase());
	        }
	        return sb.toString();
	    }

	    /**
	     * 将16进制转换为二进制
	     *
	     * @param hexStr
	     * @return
	     */
	    public static byte[] parseHexStr2Byte(String hexStr) {
	        if (hexStr.length() < 1)
	            return null;
	        byte[] result = new byte[hexStr.length() / 2];
	        for (int i = 0; i < hexStr.length() / 2; i++) {
	            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
	            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
	            result[i] = (byte) (high * 16 + low);
	        }
	        return result;
	    }

	    /**
	     * tomcat解析url方法
	     *
	     * @param map
	     * @param data
	     * @param encoding
	     * @throws java.io.UnsupportedEncodingException
	     */
	    public static void parseParameters(HashMap<Object, Object> map, String data, String encoding)
	            throws UnsupportedEncodingException {
	        if ((data == null) || (data.length() <= 0)) {
	            return;
	        }

	        byte[] bytes = null;
	        try {
	            if (encoding == null)
	                bytes = data.getBytes();
	            else
	                bytes = data.getBytes(encoding);
	        } catch (UnsupportedEncodingException uee) {
	        }
	        parseParameters(map, bytes, encoding);
	    }

	    /**
	     * tomcat解析url方法
	     *
	     * @param map
	     * @param data
	     * @param encoding
	     * @throws java.io.UnsupportedEncodingException
	     */
	    public static void parseParameters(HashMap<Object, Object> map, byte[] data, String encoding) throws UnsupportedEncodingException {
	        if ((data != null) && (data.length > 0)) {
	            int ix = 0;
	            int ox = 0;
	            String key = null;
	            String value = null;
	            while (ix < data.length) {
	                byte c = data[(ix++)];
	                switch ((char) c) {
	                    case '&':
	                        value = new String(data, 0, ox, encoding);
	                        if (key != null) {
	                            putMapEntry(map, key, value);
	                            key = null;
	                        }
	                        ox = 0;
	                        break;
	                    case '=':
	                        if (key == null) {
	                            key = new String(data, 0, ox, encoding);
	                            ox = 0;
	                        } else {
	                            data[(ox++)] = c;
	                        }
	                        break;
	                    case '+':
	                        data[(ox++)] = 32;
	                        break;
	                    case '%':
	                        data[(ox++)] = (byte) ((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));

	                        break;
	                    default:
	                        data[(ox++)] = c;
	                }
	            }

	            if (key != null) {
	                value = new String(data, 0, ox, encoding);
	                putMapEntry(map, key, value);
	            }
	        }
	    }

	    public static void putMapEntry(Map<Object, Object> map, String name, String value) {
	        String[] newValues = null;
	        String[] oldValues = (String[]) (String[]) map.get(name);
	        if (oldValues == null) {
	            newValues = new String[1];
	            newValues[0] = value;
	        } else {
	            newValues = new String[oldValues.length + 1];
	            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
	            newValues[oldValues.length] = value;
	        }
	        map.put(name, newValues);
	    }

	    public static byte convertHexDigit(byte b) {
	        if ((b >= 48) && (b <= 57)) return (byte) (b - 48);
	        if ((b >= 97) && (b <= 102)) return (byte) (b - 97 + 10);
	        if ((b >= 65) && (b <= 70)) return (byte) (b - 65 + 10);
	        return 0;
	    }
	    
	    
	    public static String DecryptDoNet(String message, String key) throws Exception {

			// System.out.println(DecryptDoNet("OtZoSiBzb+IMzhfC+m6iCpaZJQFd51xHD3+5/qnAVKX0fShgjPNeJYbUEfevvgMu+2kLXWbITZ9wYlZvY0ZblRzG5ZeXa+9Sw1pSheobJvE=","507134G0"));
			// 输出密文解密后结果，大概为/TCL/L46F2510E/MS600/LTG1300072/200/4A-LCD46O-CS1/81-PWE046-PW1/08-39F259P4-MA1
			// 先用base64解码，然后再DES解密,加密后的字符串进行Base64编码之后进行传递
			byte []aw={0x12,0x34,0x56,0x78,(byte)0x90,(byte)0xAB,(byte)0xCD,(byte)0xEF};
			sun.misc.BASE64Decoder base64Decoder = new sun.misc.BASE64Decoder();
			byte[] bytesrc = base64Decoder.decodeBuffer(message);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(aw);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			byte[] retByte = cipher.doFinal(bytesrc);
			return new String(retByte);
		}    
}
