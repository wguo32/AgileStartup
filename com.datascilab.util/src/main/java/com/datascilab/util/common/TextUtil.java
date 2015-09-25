package com.datascilab.util.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <pre>
 * Java 常用流处理工具
 * </pre>
 * 
 * @project baidamei
 * @author cevencheng <cevencheng@gmail.com>
 * @create 2012-11-29 下午5:17:32
 */
public class TextUtil {

	/**
	 * 将流另存为文件
	 * 
	 * @param is
	 * @param outfile
	 */
	public void streamSaveAsFile(InputStream is, File outfile) {

		FileOutputStream fos = null;
		try {
			File file = outfile;
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	/**
	 * Read an input stream into a string
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	static public String streamToString(InputStream in) throws IOException {

		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public static byte[] stream2Byte(InputStream is) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b, 0, b.length)) != -1) {
			baos.write(b, 0, len);
		}
		byte[] buffer = baos.toByteArray();
		return buffer;
	}

	/**
	 * @方法功能 InputStream 转为 byte
	 * @param InputStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] inputStream2Byte(InputStream inStream)
			throws Exception {

		// ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		// byte[] buffer = new byte[1024];
		// int len = -1;
		// while ((len = inStream.read(buffer)) != -1) {
		// outSteam.write(buffer, 0, len);
		// }
		// outSteam.close();
		// inStream.close();
		// return outSteam.toByteArray();
		int count = 0;
		while (count == 0) {
			count = inStream.available();
		}
		byte[] b = new byte[count];
		inStream.read(b);
		return b;
	}

	/**
	 * @方法功能 byte 转为 InputStream
	 * @param 字节数组
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream byte2InputStream(byte[] b) throws Exception {

		InputStream is = new ByteArrayInputStream(b);
		return is;
	}

	/**
	 * @功能 短整型与字节的转换
	 * @param 短整型
	 * @return 两位的字节数组
	 */
	public static byte[] shortToByte(short number) {

		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	/**
	 * @功能 字节的转换与短整型
	 * @param 两位的字节数组
	 * @return 短整型
	 */
	public static short byteToShort(byte[] b) {

		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最低位
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * @方法功能 整型与字节数组的转换
	 * @param 整型
	 * @return 四位的字节数组
	 */
	public static byte[] intToByte(int i) {

		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	/**
	 * @方法功能 字节数组和整型的转换
	 * @param 字节数组
	 * @return 整型
	 */
	public static int bytesToInt(byte[] bytes) {

		int num = bytes[0] & 0xFF;
		num |= ((bytes[1] << 8) & 0xFF00);
		num |= ((bytes[2] << 16) & 0xFF0000);
		num |= ((bytes[3] << 24) & 0xFF000000);
		return num;
	}

	/**
	 * @方法功能 字节数组和长整型的转换
	 * @param 字节数组
	 * @return 长整型
	 */
	public static byte[] longToByte(long number) {

		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(temp & 0xff).byteValue();
			// 将最低位保存在最低位
			temp = temp >> 8;
			// 向右移8位
		}
		return b;
	}

	/**
	 * @方法功能 字节数组和长整型的转换
	 * @param 字节数组
	 * @return 长整型
	 */
	public static long byteToLong(byte[] b) {

		long s = 0;
		long s0 = b[0] & 0xff;// 最低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 最低位
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff; // s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStreamToByte(InputStream inStream)
			throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static String readStreamToString(InputStream inStream)
			throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toString();
	}

	/**
	 * 将输入流转化成某字符编码的String
	 * 
	 * @param inStream
	 *            输入流
	 * @param encoding
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String readStreamToString(InputStream inStream,
			String encoding) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return new String(outStream.toByteArray(), encoding);
	}

	/**
	 * 利用byte数组转换InputStream------->String <功能详细描述>
	 * 
	 * @param in
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

	public static String Inputstr2Str_byteArr(InputStream in, String encode) {

		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = "utf-8";
			}
			while ((len = in.read(b)) != -1) {
				sb.append(new String(b, 0, len, encode));
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 利用ByteArrayOutputStream：Inputstream------------>String <功能详细描述>
	 * 
	 * @param in
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String Inputstr2Str_ByteArrayOutputStream(InputStream in,
			String encode) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = "utf-8";
			}
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			return out.toString(encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述>
	 * 
	 * @param inStr
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static InputStream Str2Inputstr(String inStr) {

		try {
			// return new ByteArrayInputStream(inStr.getBytes());
			// return new ByteArrayInputStream(inStr.getBytes("UTF-8"));
			return new StringBufferInputStream(inStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	final static int BUFFER_SIZE = 4096;

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "ISO-8859-1");
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding)
			throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "ISO-8859-1");
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringTOInputStream(String in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(
				in.getBytes("ISO-8859-1"));
		return is;
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream byteTOInputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String byteTOString(byte[] in) throws Exception {

		InputStream is = byteTOInputStream(in);
		return InputStreamTOString(is);
	}

	public static String gb2312ToUtf8(String str) {

		String urlEncode = "";

		try {

			urlEncode = URLEncoder.encode(str, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return urlEncode;

	}
}
