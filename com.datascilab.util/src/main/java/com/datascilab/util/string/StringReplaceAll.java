package com.datascilab.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplaceAll {

	public static void main(String[] args) {
		String s = "";
		System.out.println(s.replaceAll("\t\n\r", ""));

		Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
		Matcher m = CRLF.matcher("");
		String myString = "fff";
		if (m.find()) {
			myString = m.replaceAll("<br>");
		}
		// 第二种方式

		myString = myString.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		myString = myString.replaceAll(" ", "");
	}

}
