package cn.javabook.cloud.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.javabook.cloud.core.constant.GlobalConstant;
import cn.javabook.cloud.core.parent.BaseUtil;

/**
 * 日期工具类
 *
 */
public class DateUtil extends BaseUtil {
	private static final long serialVersionUID = 5535447822104667977L;

	private static final DateFormat sdf = new SimpleDateFormat(GlobalConstant.DATE_FORMAT_DATE_SECOND);

	// 获得格式化日期
	public static String parse(Date date) {
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 获取多久之前的数据
	public static String before(int before) {
		return parse(new Date(System.currentTimeMillis() - before * 1000));
	}
}
