package com.datascilab.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

public class PhoneNumberFinder {

	public static String getPhone(String in) {

		return getPhone(in, false);
	}

	public static String getPhone(String in, boolean more) {// cope with

															// situation like
															// l3九799陆o0O;

		if (Strings.isNullOrEmpty(in)) {
			return null;
		}
		String str = "";
		if (Strings.isNullOrEmpty(in)) {
			return str;
		}
		Pattern p = Pattern
				.compile("([0-9|壹|贰|叁|肆|伍|陆|柒|捌|玖|零|一|二|三|四|五|六|七|八|九| |O|Z|z|o|l]{7,})");
		Matcher m = p.matcher(in);
		while (m.find()) {
			if (more) {
				str += m.group() + ",";
			} else {
				str += m.group();
				break;
			}
		}
		if(Strings.isNullOrEmpty(str)){
			str=null;
		}

		return str;
	}

	public static String getPhonePrecise(String in) {

		return getPhonePrecise(in, false);
	}

	private static String getPhonePrecise(String in, boolean more) {

		if (Strings.isNullOrEmpty(in)) {
			return null;
		}

		// 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		// 联通：130、131、132、152、155、156、185、186
		// 电信：133、153、180、189、（1349卫通）
		String str = null;
		String regExp = "[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}";// TODO

		Pattern p = Pattern
				.compile("((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}");

		Matcher m = p.matcher(in);
		while (m.find()) {
			if (more) {
				str += m.group() + ",";
			} else {
				str += m.group();
				break;
			}
		}

		return str;

	}

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
