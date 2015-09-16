package com.datascilab.util.common;

import java.io.IOException;
import java.io.InputStream;

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
				// read text
				String s = EntityUtils.toString(entity);//Paradigms
				// encoding shoule be considered TODO
				htmlAsString = new String(EntityUtils.toString(entity)
						.getBytes("ISO_8859_1"), "UTF-8");

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

}
