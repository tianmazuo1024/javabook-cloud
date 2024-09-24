package cn.javabook.cloud.core.utils;

import cn.javabook.cloud.core.constant.GlobalConstant;
import cn.javabook.cloud.core.parent.BaseUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付工具类
 * 
 */
public class PayUtil extends BaseUtil {
	private static final long serialVersionUID = -1223919855130192512L;

	// 余额支付时生成支付订单号
	public static String generatorTransNo() {
		String date = new SimpleDateFormat(GlobalConstant.DATE_FORMAT_SECOND).format(new Date());
		return date + System.currentTimeMillis();
	}

	// 生成商户支付订单号
	public static String generatorPaySequence(String content) {
		// 生成支付订单号规范：yyyyMMddMD5后14位
		String date1 = new SimpleDateFormat(GlobalConstant.DATE_FORMAT_DAY).format(new Date());
		String date2 = new SimpleDateFormat(GlobalConstant.DATE_FORMAT_SECOND).format(new Date());
		String md5str = date2 + content;
		return date1 + MD5Util.md5encode(md5str, GlobalConstant.CHAR_CODE_UTF8).toUpperCase().substring(19);
	}

}
