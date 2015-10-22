package com.datascilab.util.common.json;

import java.io.FileInputStream;

import com.google.gson.Gson;

public class GsonUtil<T> {

	public final static Gson gson = new Gson();

	public static String getGsonStr(Object obj) {

		return gson.toJson(obj);
	}

	public T toBean(String s, Class t) {

		return (T) gson.fromJson(s, t);

	}

	public static void main(String[] args) {

		String path = "/Volumes/s1/agents.txt";

		// File f = new File();

		FileInputStream f;
		Gson gson= new Gson();
	
	}

}
