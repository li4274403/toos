package com.lh.tools;

public class NumUtil {

	/**
	 * 整数转为大写汉字
	 * 
	 * @param value
	 * @return
	 */
	public static String numToChinese(int value) {
		String result = null;
		for (int i = String.valueOf(value).length() - 1; i >= 0; i--) {
			int r = (int) (value / Math.pow(10, i));
			result += UtilConstant.NUM_UPPER_CASE[r % 10]
					+ UtilConstant.NUM_UNITS[i];
		}
		result = result.replaceAll("零[十, 百, 千]", "零");
		result = result.replaceAll("零+", "零");
		result = result.replaceAll("零([万, 亿])", "$1");
		result = result.replaceAll("亿万", "亿");
		if (result.startsWith("一十"))
			result = result.substring(1);
		if (result.endsWith("零"))
			result = result.substring(0, result.length() - 1);
		return result;
	}

}
