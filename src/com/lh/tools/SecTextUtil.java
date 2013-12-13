package com.lh.tools;

public abstract class SecTextUtil {

	/**
	 * 16进制数字符串将转换为正常字符串
	 * 
	 * @param content
	 *            字符串内容
	 * @return
	 */
	public static String uncoding(String content) {
		StringBuilder sbUnc = new StringBuilder();
		StringBuilder sbTmp = null;
		for (int i = -1; i < content.length() - 1;) {
			sbTmp = new StringBuilder();
			sbTmp.append(String.valueOf(content.charAt(++i)));
			sbTmp.append(String.valueOf(content.charAt(++i)));
			sbTmp.append(String.valueOf(content.charAt(++i)));
			sbTmp.append(String.valueOf(content.charAt(++i)));
			sbUnc.append(toStrHex(sbTmp.toString()));
		}
		return sbUnc.toString();
	}

	/**
	 * 正常字符串转换为16进制数字符串将
	 * 
	 * @param content
	 *            十六进制 字符串内容
	 * @return
	 */
	public static String encoding(String content) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < content.length(); i++) {
			sb.append(toHexStr(Integer.toHexString(content.charAt(i))));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 加密码文本内容。
	 * 
	 * @param context
	 *            文本信息。
	 * @param seckey
	 *            密钥文本信息
	 * @return
	 */
	public static String sec(String context, String seckey) {
		if (seckey == null || seckey.length() == 0)
			seckey = UtilConstant.TEST_SEC_CODE;
		StringBuilder sb = new StringBuilder();
		String hexStr = encoding(context);
		String secWord = encoding(seckey);
		char fs, sec;
		int l = secWord.length();
		for (int i = 0; i < hexStr.length(); i++) {
			fs = hexStr.charAt(i);
			sec = secWord.charAt(i % l);
			sb.append(secChar(fs, sec));
		}
		return sb.toString();
	}

	/**
	 * 解密加密后的十六进制信息
	 * 
	 * @param context
	 *            加密码后的十六进制代码
	 * @param seckey
	 *            密钥
	 * @return
	 */
	public static String unsec(String context, String seckey) {
		if (seckey == null || seckey.length() == 0)
			seckey = UtilConstant.TEST_SEC_CODE;
		StringBuilder sb = new StringBuilder();
		String secWord = encoding(seckey);
		char fs, sec;
		int l = secWord.length();
		for (int i = 0; i < context.length(); i++) {
			fs = context.charAt(i);
			sec = secWord.charAt(i % l);
			sb.append(unSecChar(fs, sec));
		}
		String hexStr = uncoding(sb.toString());
		return hexStr;
	}

	/**
	 * 16进制数字符串将转换为长度为4不够长度时在前补0[私有方式禁止外部类调用]
	 * 
	 * @param hexStr
	 *            16进制数字符串
	 * @return
	 */
	private static String toHexStr(String hexStr) {
		int length = hexStr.length();
		if (length < 4) {
			return String.format("%0" + (4 - length) + "d", 0) + hexStr;
		}
		return hexStr;
	}

	/**
	 * 16进制数字符串将转换为正常字符串[私有方式禁止外部类调用]
	 * 
	 * @param hexStr
	 *            16进制数字符串长度为4
	 * @return
	 */
	private static String toStrHex(String str) {
		char val = (char) Integer.valueOf(str, 16).intValue();
		String hexStr = String.valueOf(val);
		return hexStr;
	}

	/**
	 * 加密字符串
	 * 
	 * @param fs
	 * @param sec
	 * @return
	 */
	private static char secChar(char fs, char sec) {
		int index = UtilConstant.TEST_SEC_REP_CODE.indexOf(fs)
				+ UtilConstant.TEST_SEC_REP_CODE.indexOf(sec);
		index = index % UtilConstant.TEST_SEC_REP_CODE.length();
		return UtilConstant.TEST_SEC_REP_CODE.charAt(index);
	}

	/**
	 * 解密字符串
	 * 
	 * @param fs
	 * @param sec
	 * @return
	 */
	private static char unSecChar(char fs, char sec) {
		int index = UtilConstant.TEST_SEC_REP_CODE.indexOf(fs)
				- UtilConstant.TEST_SEC_REP_CODE.indexOf(sec);
		index = index % UtilConstant.TEST_SEC_REP_CODE.length();
		if (index < 0)
			index = UtilConstant.TEST_SEC_REP_CODE.length() + index;
		return UtilConstant.TEST_SEC_REP_CODE.charAt(index);
	}
}
