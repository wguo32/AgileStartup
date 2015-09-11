package com.datascilab.util.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OneFileOneString {

	public static void main(String[] args) throws Exception {

		File file = new File("d:/d.txt");

		StringBuilder sb = new StringBuilder();
		String s = "";
		BufferedReader br = new BufferedReader(new FileReader(file));

		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}

		br.close();
		String str = sb.toString();
		// str是你想要的东西
	}

}
