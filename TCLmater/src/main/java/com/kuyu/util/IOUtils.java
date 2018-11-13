package com.kuyu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import com.kuyu.common.CommonConstants;

//输入输出流
public class IOUtils {
	public static final int BUFFER_SIZE = 1024 << 1;

	public static void closeQuietly(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException cause) {

		}
	}

	public static void closeQuietly(Writer writer) {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (IOException cause) {

		}
	}

	//从输入流中读取字符
	public static String readLine(InputStream input, String encode) throws IOException {
		String text = "";
		if (input != null) {
			if (StringUtil.isEmpty(encode)) {
				encode = CommonConstants.DEFAULT_DENCODE;
			}
			StringBuffer textBuffer = new StringBuffer();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(input,"utf-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					textBuffer.append(line);
				}
				text = textBuffer.toString();
			} finally {
				closeQuietly(reader);
			}
		}
		return text;

	}

	// 输入流的关闭
	public static void closeQuietly(InputStream input) {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException cause) {

		}
	}

	// 输出流的关闭
	public static void closeQuietly(OutputStream output) {
		try {
			if (output != null) {
				output.close();
			}
		} catch (IOException cause) {

		}
	}

	// 用于二进制流的拷贝
	public static int copy(InputStream input, OutputStream output,int bufferSize)throws IOException {
		int count = 0;
		byte[] buffer =null;
		try {
			if (input != null && output != null) {
				int size = BUFFER_SIZE;
				if(bufferSize >BUFFER_SIZE){
					size =  bufferSize;
				}
				buffer = new byte[size];
				int readedBytes = -1;
				while ((readedBytes = input.read(buffer)) != -1) {
					output.write(buffer, 0, readedBytes);
					count += readedBytes;
				}
				output.flush();
			}

		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
			buffer =null;
		}
		return count;
	}

	public static int copy(Reader input, Writer output,int bufferSize) throws IOException {
		int count = 0;
		char[] buffer =null;
		try {
			if (input != null && output != null) {
				int size = BUFFER_SIZE;
				if(bufferSize >BUFFER_SIZE){
					size =  bufferSize;
				}
				buffer = new char[size];
				int readedBytes = -1;
				while ((readedBytes = input.read(buffer)) != -1) {
					output.write(buffer, 0, readedBytes);
					count += readedBytes;
				}
				output.flush();
			}
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
			buffer =null;
		}
		return count;
	}

}
