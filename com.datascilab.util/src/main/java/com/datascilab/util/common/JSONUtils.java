package com.datascilab.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {

	private Logger log = LoggerFactory.getLogger(JSONUtils.class);

	private static class JSONUtilsHolder {

		private static final JSONUtils INSTANCE = new JSONUtils();
	}

	public static JSONUtils getInstance() {
		return JSONUtilsHolder.INSTANCE;
	}


}
