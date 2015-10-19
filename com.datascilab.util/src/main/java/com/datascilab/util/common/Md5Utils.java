package com.datascilab.util.common;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class Md5Utils {

	public static String md5(String str) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(str.getBytes());
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

}
