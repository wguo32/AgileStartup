package com.datascilab.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlFinder {

	public static void main(String[] args) {
	String host=	UrlFinder.getHost("http://esf.sh.fang.com/house-a025/i32/");
	System.out.println(host);

	}
	
	public static String getHost(String url){
        if(url==null||url.trim().equals("")){
            return "";
        }
        String host = "";
        Pattern p =  Pattern.compile("http://(?<=//|)((\\w)+\\.)+\\w+/");
        Matcher matcher = p.matcher(url);  
        if(matcher.find()){
            host = matcher.group();  
        }
        return host;
    }
	
	public static String getHost2(String url){ //without http://
        if(url==null||url.trim().equals("")){
            return "";
        }
        String host = "";
        Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+/");
        Matcher matcher = p.matcher(url);  
        if(matcher.find()){
            host = matcher.group();  
        }
        return host;
    }
	


}
