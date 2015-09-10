package com.datascilab.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class NumUtil {

	Logger log = LoggerFactory.getLogger(NumUtil.class);

	private static class NumUtilHolder {
		private static NumUtil instance = new NumUtil();
	}

	public static NumUtil getInstance() {

		return NumUtilHolder.instance;

	}

	public double toDouble(String s) {
		double value = 0;
		if (!Strings.isNullOrEmpty(s)) {
			try {
				value = Double.valueOf(s);
			} catch (Exception e) {
				log.warn("NumUtil.toDouble error : s-> {}", s);
			}
		}
		return value;
	}
	
	public Integer toInteger(String s) {
		Integer value = 0;
		if (!Strings.isNullOrEmpty(s)) {
			try {
				value = Integer.valueOf(s);
			} catch (Exception e) {
				log.warn("NumUtil.toInteger error : s-> {}", s);
			}
		}
		return value;
	}

}
