package com.tledu.aaa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static String regexIP = "(([0,1]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([0,1]?\\d?\\d|2[0-4]\\d|25[0-5])";
	//校验IP格式
	public static boolean	isValidIP(String ip) {
		return isVaid(regexIP, ip);
	}
	
	
	
	
	//验证格式
	public static boolean isVaid(String regexPattern, String input) {
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	//取数据
	public static String getMatchContent(String regexPattern, String input,
			int groupIndex) {
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}
		return null;
	}
}
