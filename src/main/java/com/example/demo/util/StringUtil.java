package com.example.demo.util;

public class StringUtil {
	public static boolean isNull(String str) {
		if("".equals(str) || null == str || 0==str.length() || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	

}
