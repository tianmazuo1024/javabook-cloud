package cn.javabook.chapter10.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 */
public class DateUtils {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String parse(final Date date) {
		try {
			if (date == null) {
				return null;
			}
			return sdf.format(date);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static long string2Stamp(String date) {
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		return 0;
	}
}
