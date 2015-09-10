package com.datascilab.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberFinder {

	public static void main(String[] args) {
		String mobiles = "1324884540  fdfd dfd";
	     String regExp = "[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}";
		// 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		// 联通：130、131、132、152、155、156、185、186
		// 电信：133、153、180、189、（1349卫通）
		Pattern p = Pattern
				.compile("((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}");

		Matcher m = p.matcher(mobiles);
		while (m.find()) {

		}

	}

}
