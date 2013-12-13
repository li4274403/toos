package com.lh.tools;

public abstract class StringUtil {

	/**
	 * 检查字符串是否为空、空字符串、空格字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

}
