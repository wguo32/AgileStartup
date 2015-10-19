package com.datascilab.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

public class ImgFinder {

	public static String getImg(String in, boolean more) {// cope with

		if (Strings.isNullOrEmpty(in)) {
			return null;
		}
		String str = "";
		if (Strings.isNullOrEmpty(in)) {
			return str;
		}
		Pattern p = Pattern
				.compile("http://([0-9a-zA-Z|_|-|/|.]+).([jpg|png])");
		Matcher m = p.matcher(in);
		while (m.find()) {
			if (more) {
				str += m.group() + ",";
			} else {
				str += m.group();
				break;
			}
		}
		if (Strings.isNullOrEmpty(str)) {
			str = null;
		}

		return str;
	}

	public static void main(String[] args) {

		String s = "<img src='http://img4.douban.com/view/group_topic/large/public/p36655598.jpg' alt= class= />";
		System.out.println(ImgFinder.getImg(s, true));

	}

}
