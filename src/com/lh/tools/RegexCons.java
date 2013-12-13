package com.lh.tools;

public abstract class RegexCons {
	/**
	 * email地址 <br/>
	 * 格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
	 */
	public static final String EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";

	/**
	 * 验证身份证号码<br/>
	 * 居民身份证号码15位或18位，最后一位可能是数字或字母
	 */
	public static final String IDCARD = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * 
	 * 移动、联通、电信运营商的号码段
	 * <p>
	 * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 * </p>
	 * <p>
	 * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 * </p>
	 * <p>
	 * 电信的号段：133、153、180（未启用）、189
	 * </p>
	 */
	public static final String MOBILE = "(\\+\\d+)?1[3458]\\d{9}$";

	/**
	 * 验证固定电话号码<br/>
	 * 
	 * 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	 * <p>
	 * <b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	 * 数字之后是空格分隔的国家（地区）代码。
	 * </p>
	 * <p>
	 * <b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	 * 对不使用地区或城市代码的国家（地区），则省略该组件。
	 * </p>
	 * <p>
	 * <b>电话号码：</b>这包含从 0 到 9 的一个或多个数字
	 * </p>
	 */
	public static final String PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";

	/**
	 * 验证整数（正整数和负整数） <br/>
	 * 一位或多位0-9之间的整数
	 */
	public static final String DIGIT = "\\-?[1-9]\\d+";

	/**
	 * 验证整数和浮点数（正负整数和正负浮点数） <br/>
	 * 一位或多位0-9之间的浮点数，如：1.23，233.30
	 */
	public static final String DECIMALS = "\\-?[1-9]\\d+(\\.\\d+)?";

	/**
	 * 验证空白字符<br/>
	 * 空白字符，包括：空格、\t、\n、\r、\f、\x0B
	 */
	public static final String BLANK_SPACE = "\\s+";

	/**
	 * 验证中文
	 */
	public static final String CHINESE = "^[\u4E00-\u9FA5]+$";

	/**
	 * 验证日期（年月日）<br/>
	 * 格式：1992-09-03，或1992.09.03
	 */
	public static final String DATE_YYYY_MM_DD = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";

	/**
	 * 验证URL地址<br/>
	 * 格式：http://blog.csdn.net
	 */
	public static final String URL = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";

	/**
	 * 匹配中国邮政编码
	 */
	public static final String CHINESE_POST_CODE = "[1-9]\\d{5}";

	/**
	 * 匹配IP地址<br/>
	 * (简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小) IPv4标准地址
	 */
	public static final String IP_ADDRESS = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";

}
