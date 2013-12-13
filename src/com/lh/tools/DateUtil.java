package com.lh.tools;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

	/**
	 * 日期转字符串转
	 * 
	 * @param date
	 *            日期
	 * @param dfStr日期格式
	 * @return
	 */
	public static String toStr(Date date, String dfStr) {
		String dtval = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(dfStr);
			dtval = df.format(date);
		} catch (Exception e) {
		}
		return dtval;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dtStr
	 *            日期字符串
	 * @return
	 */
	public static Date toDate(String dtStr) {
		Date date = null;
		for (int i = 0; i < UtilConstant.df.length; i++) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(UtilConstant.df[i]);
				date = sdf.parse(dtStr);
			} catch (Exception e) {
			}
			if (date != null)
				break;
		}
		return date;
	}

	/**
	 * 计算丙个时间相差的天数，ROUND_HALF_DOWN
	 * 
	 * @param bigDate
	 *            截止日期
	 * @param smDate
	 *            开始日期
	 * @return 正数截止日期大于开始日期，负数相反
	 */
	public static int subtractDay(Date bigDate, Date smDate) {
		long millis1 = bigDate.getTime();
		long millis2 = smDate.getTime();
		BigDecimal rst = new BigDecimal(millis1 - millis2).divide(
				new BigDecimal(1000 * 60 * 60 * 24), 0,
				BigDecimal.ROUND_HALF_DOWN);
		return rst.intValue();
	}

	public static void main(String[] args) {

	}
}
