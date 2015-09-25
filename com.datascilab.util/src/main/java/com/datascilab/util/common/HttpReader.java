package com.datascilab.util.common;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

//TODO
public class HttpReader {

	public static String example_xpath = "//*[@id='content']/x:div[2]/x:div[2]/x:div[2]/x:div/x:div[1]/x:div[2]/x:p/x:a[1]"
			.replace("x:", "").replace("html", "");

	String url = "http://news.yahoo.com";

	InputStream in = null;

	HtmlCleaner cleaner = new HtmlCleaner();

	TagNode rootNode = null;

	String htmlAsString = null;

	public HttpReader(String url) {

		super();
		this.url = url;
		init();
	}

	public void init() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000).build();// 设置请求和传输超时时间
		HttpGet httpget;
		HttpResponse response;
		String encode;
		try {
			httpget = new HttpGet(url);
			httpget.setConfig(requestConfig);
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (entity != null) {
					entity = new BufferedHttpEntity(entity);
				}
				entity.getContentType();
				// read stream
				in = entity.getContent();
				// ----------------
				rootNode = cleaner.clean(in, "UTF-8");
				encode = getFileEncode(url);
				if (encode.toLowerCase().contains("utf")) {
					htmlAsString = EntityUtils.toString(entity);
				} else if (encode.toLowerCase().contains("windows")) {
					htmlAsString = EntityUtils.toString(entity);
				} else if (encode.toLowerCase().contains("gb")) {
					htmlAsString = Inputstr2Str_byteArr(in,
							encode.toUpperCase());
					htmlAsString = new String(htmlAsString.getBytes(), "UTF-8");
				} else if (encode.toLowerCase().contains("iso")) {
					htmlAsString = new String(EntityUtils.toString(entity)
							.getBytes("ISO_8859_1"), "UTF-8");
				} else {
					htmlAsString = EntityUtils.toString(entity);
				}

			} else {
				System.out.println("erro-------r");
			}
			httpget.abort();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		try {
			if (httpclient != null) {
				httpclient.close();
			}

			if (in != null) {
				in.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String get(String xpath) {

		String rtn = null;
		Object[] o;
		try {
			o = rootNode.evaluateXPath(xpath);
			if (o.length > 0) {
				TagNode tn = (TagNode) o[0];
				rtn = cleaner.getInnerHtml(tn);
			}
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}

	public String getText(String xpath) {

		String rtn = null;
		Object[] o;
		try {
			o = rootNode.evaluateXPath(xpath);
			if (o.length > 0) {
				TagNode tn = (TagNode) o[0];
				rtn = tn.getText().toString();
			}
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public InputStream getIn() {

		return in;
	}

	public HtmlCleaner getCleaner() {

		return cleaner;
	}

	public TagNode getNode() {

		return rootNode;
	}

	public String getHtmlAsString() {

		return htmlAsString;
	}

	public static void main(String[] args) {

		HttpReader hr = new HttpReader("http://news.yahoo.com");
		String s = hr.getHtmlAsString();
		System.out.println(s.replaceAll("[\r|\n\t]", ""));
	}

	public String getFileEncode(String urlStr) throws MalformedURLException,
			IOException {

		URL url = new URL(urlStr);
		CodepageDetectorProxy codepageDetectorProxy = CodepageDetectorProxy
				.getInstance();

		codepageDetectorProxy.add(JChardetFacade.getInstance());
		codepageDetectorProxy.add(ASCIIDetector.getInstance());
		codepageDetectorProxy.add(UnicodeDetector.getInstance());
		codepageDetectorProxy.add(new ParsingDetector(false));
		codepageDetectorProxy.add(new ByteOrderMarkDetector());

		Charset charset = codepageDetectorProxy.detectCodepage(url);
		return charset.name();

	}

	/**
	 * 利用BufferedReader实现Inputstream转换成String <功能详细描述>
	 * 
	 * @param in
	 * @return String
	 */

	public static String Inputstr2Str_Reader(InputStream in, String encode) {

		String str = "";
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = "utf-8";
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, encode));
			StringBuffer sb = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				sb.append(str).append("\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
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

}
