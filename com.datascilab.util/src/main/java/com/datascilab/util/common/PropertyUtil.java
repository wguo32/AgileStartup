package com.datascilab.util.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	Properties p;
	

	private static class PropertyUtilHolder {
		private static PropertyUtil instance = new PropertyUtil();
	}

	public static PropertyUtil getInstance() {

		return PropertyUtilHolder.instance;

	}

	public PropertyUtil() {
		super();
		init();
	}

	
	
	private void init() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("config.properties");

		 p = new Properties();

		try {
			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public String get(String key){
		
		return p.getProperty(key);
		
	}
	
	public static void main(String[] args) {
	
		System.out.println(PropertyUtil.getInstance().get("htmlRootFolder.lianjia")); ;
	}

}
