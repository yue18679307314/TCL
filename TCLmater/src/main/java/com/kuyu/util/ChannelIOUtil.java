package com.kuyu.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class ChannelIOUtil {
	protected static final Logger log = Logger.getLogger(ChannelIOUtil.class);

	public static final int IMAGE_SIZE_LIMIT = 1024; // 128KB
	public static final int VOICE_SIZE_LIMIT = 2048; // 256KB
	public static final int VIDEO_SIZE_LIMIT = 10240; // KB

	/**
	 * 通过媒体文件id 下载图片到本地   --通关
	 * 
	 * @param accessToken
	 * @param mediaId
	 *            媒体文件Id
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static File downLoadImage(String accessToken, String mediaId, String fileName, String imgUrl ) {
		File file = null;
		// 定义个客户端
		HttpClient client = new HttpClient();
		// 下载文件得到字节流
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get" + "?access_token=" + accessToken + "&media_id=" + mediaId;
		log.info("[url:" + url + "]");
		GetMethod getMethod = new GetMethod(url);
		InputStream ips = null;
		FileOutputStream fops = null;
		boolean isLoged = false;
		for (int i = 1; i < 4; i++) {
			try {
				int status = client.executeMethod(getMethod);
				if (HttpStatus.SC_OK == status) {
					isLoged = true;
					break;
				}
				String res = getMethod.getResponseBodyAsString();
				log.info("http request status:" + status + ", response:" + res);
				Thread.sleep(200L);
			} catch (IOException e) {
				log.error("请求微信下载url[" + i + "]", e);
				isLoged = false;
			} catch (InterruptedException e) {
				isLoged = false;
				log.error("请求微信下载url[" + i + "]", e);
			}
		}
		if (!isLoged) {
			log.info("请求微信下载接口，3次都没调通.返回");
			return null;
		}
		try {
			Header contentType = getMethod.getResponseHeader("Content-Type");
			String type = "";
			if (contentType != null) {
				type = contentType.getValue();
				int flag = type.indexOf("/");
				if (flag > 0) {
					type = type.substring(0, flag);
				}
			}
			log.info("download file [type=" + type + "]");
			if (StringUtil.isEmpty(type) || (!"video".equalsIgnoreCase(type) && !"audio".equalsIgnoreCase(type) && !"image".equalsIgnoreCase(type))) {
				String res = getMethod.getResponseBodyAsString();
				log.warn("download file not is [video/audio/image],[mediaId=" + mediaId + ",fileName=" + fileName + ";response=" + res + "]");
				return null;
			}
//			// 检查需下载文件的大小，是否超出限制
//			if (checkLength) {
//				if (!checkRequestLengthLimit(getMethod, type)) {
//					reqResult.put("errorMsg", "文件大小超出限制\n图片（image）: 128K;\n语音（voice）：256K;\n视频（video）：1MB.");
//					log.info("downLoadMedia file size out limit [mediaId=" + mediaId + ",fileName=" + fileName + "]");
//					return null;
//				}
//			}
			String fileEx = ".jpg";
			Header disposition = getMethod.getResponseHeader("Content-disposition");
			log.info("header->Content-disposition:" + disposition.getValue());
			HeaderElement[] elements = null;
			if (disposition != null && (elements = disposition.getElements()).length > 0) {
				String fName = elements[0].getParameterByName("filename").getValue();
				fileEx = fName.substring(fName.lastIndexOf(".")); // 文件扩展名
			}
			ips = getMethod.getResponseBodyAsStream(); // 在内存中构建文件
			
			String tmp = imgUrl + File.separator;
			log.error("tmp ----"+tmp);
			file = new File(tmp + fileName + fileEx);
			if (file.exists() && !file.delete()) {
				file.delete();
			}
			createFolder(file);
			fops = new FileOutputStream(file); // 一次读写1k
			byte[] b = new byte[1024 * 10];
			int count = 0;
			while ((count = ips.read(b)) != -1) {
				fops.write(b, 0, count);
			}
			fops.flush();
		} catch (HttpException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		} finally {
			try {
				if (ips != null) {
					ips.close();
				}
			} catch (IOException e) {
			}
			try {
				if (fops != null) {
					fops.close();
				}
			} catch (IOException e) {

			}
		}
		log.info("download file path[mediaID=" + mediaId + ",filepath=" + file + "]");
		
		return file;
	}
	

	/**
	 * 根据图片的url 下载到指定目录，后缀根据url的图片来
	 * 
	 * @param imgUrl
	 * @param outFile
	 *            绝对路径带文件名。不带扩展名【url: www.sf-express.com/image/图标.jpg ->
	 *            outFile: D:/tmp/图标1 】
	 * @param ex
	 *            生成图片的扩展名
	 * @return
	 */
	public static File downLoadImage(String imgUrl, String outFile, String ex) {

		try {
			if (StringUtil.isEmpty(outFile)) {
				outFile = System.getProperty("java.io.tmpdir") + File.separator + "temp.jpg";
			}
			if (StringUtil.isEmpty(ex) && outFile.contains(".")) { // 如果没有指定扩展名
				String fileEx = imgUrl.substring(imgUrl.lastIndexOf(".")); // 文件扩展名
				outFile += fileEx;
			} else {
				outFile += ex;
			}
			log.error("downLoad image [imgurl=" + imgUrl + ",outFile=" + outFile + "]");
			File imageFile = new File(outFile);
			createFolder(imageFile);
			URL url = new URL(imgUrl);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
			return imageFile;
		} catch (MalformedURLException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	/**
	 * 读取输入流的数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	/**
	 * 取得文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File f) throws Exception {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			log.error("文件不存在");
		}
		return s;
	}

	/**
	 * 纵向合并两张图片 [jpg]
	 * 
	 * @param img1
	 *            第一张图片
	 * @param img2
	 *            第二张图片
	 * @param fileName
	 *            合并后文件名
	 * @param outFile
	 *            合并图片路径
	 */
	public static File mergeYPic(String img1, String img2, String fileName, String outFolder) {// 纵向处理图片
		try {
			/* 1 读取第一张图片 */
			File fileOne = new File(img1);
			BufferedImage imageFirst = ImageIO.read(fileOne);
			int width1 = imageFirst.getWidth();// 图片宽度
			int height1 = imageFirst.getHeight();// 图片高度
			int[] imageArrayFirst = new int[width1 * height1];// 从图片中读取RGB
			imageArrayFirst = imageFirst.getRGB(0, 0, width1, height1, imageArrayFirst, 0, width1);

			/* 1 对第二张图片做相同的处理 */
			File fileTwo = new File(img2);
			BufferedImage imageSecond = ImageIO.read(fileTwo);
			int width2 = imageSecond.getWidth();// 图片宽度
			int height2 = imageSecond.getHeight();// 图片高度
			int[] imageArraySecond = new int[width2 * height2];
			imageArraySecond = imageSecond.getRGB(0, 0, width2, height2, imageArraySecond, 0, width2);

			int maxWidth = width1 > width2 ? width1 : width2;

			// 生成新图片
			BufferedImage imageResult = new BufferedImage(maxWidth, height1 + height2, BufferedImage.TYPE_INT_RGB);
			imageResult.setRGB(0, 0, width1, height1, imageArrayFirst, 0, width1);// 设置左半部分的RGB
			imageResult.setRGB(0, height1, width2, height2, imageArraySecond, 0, width2);// 设置右半部分的RGB
			if (StringUtil.isEmpty(outFolder)) {
				outFolder = System.getProperty("java.io.tmpdir") + File.separator;
			}
			File file = new File(outFolder + fileName + ".jpg");
			createFolder(file); // 创建父目录
			log.error("merge image ==> [img1=" + img1 + ",img2=" + img2 + ",mergeImg=" + file + "]");
			ImageIO.write(imageResult, "jpg", file);// 写图片
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把文件转成字节
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] fileToBytes(File file) {
		byte[] bytes = null;
		if (file != null && file.exists()) {
			try {
				log.info("file convert to byte[file=" + file.getAbsolutePath() + "]");
				Long lt = file.length();
				if (lt > Integer.MAX_VALUE) {
					log.warn("file length > Integer MAX_VALUE");
					return null;
				}
				bytes = new byte[Integer.parseInt(lt.toString())];

				InputStream in = new FileInputStream(file);
				in.read(bytes, 0, bytes.length);
				in.close();
				log.info("file byte[] length:" + bytes.length);
				return bytes;
			} catch (FileNotFoundException e) {
				log.error("fileToBytes() file not found:", e);
			} catch (IOException e) {
				log.error("fileToBytes() error:", e);
			}
		}
		return null;
	}

	/**
	 * 创建文件父目录
	 * 
	 * @param file
	 */
	public static void createFolder(File file) {
		if (file == null) {
			log.warn("file is null");
			return;
		}
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (!parentFile.exists() && !parentFile.mkdirs()) {
				log.warn(file.getParent() + " create fail !");
			}
		}
	}

	/**
	 * 格式化时间为指定模式的字符串
	 * 
	 * @param date 时间, 使用 当前时间
	 * @param fmtPattern 格式化模式  使用'yyyyMMdd'
	 * @return
	 */
	public static String getFmtDate(Date date, String fmtPattern) {
		if (date == null) {
			date = new Date();
		}
		if (StringUtil.isEmpty(fmtPattern)) {
			fmtPattern = "yyyyMMdd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmtPattern);
		return sdf.format(date);
	}

}
