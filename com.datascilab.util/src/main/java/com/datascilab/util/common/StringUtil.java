package com.datascilab.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.common.base.Strings;

public class StringUtil {

	private static class StringUtilHolder {

		private static final StringUtil INSTANCE = new StringUtil();
	}

	public static StringUtil getInstance() {
		return StringUtilHolder.INSTANCE;
	}

	public String fileToString(String fileName, String encoding) {
		String rtn = null;
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			rtn = fileToString(file, encoding);
		}

		return rtn;

	}

	public String toStringWithout_n_r(String input) {
		String rtn = input;
		if (!Strings.isNullOrEmpty(input)) {
			rtn = rtn.replaceAll("\\s*|\t|\r|\n", "");
		}
		return rtn;
	}

	public String fileToString(File file, String encoding) {

		if (file != null) {
			if (!file.exists() || (file.exists() && !file.isFile())) {
				return null;
			}
		} else {
			return null;
		}

		if (Strings.isNullOrEmpty(encoding)) {
			encoding = "utf-8";
		}
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

}
