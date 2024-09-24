package cn.javabook.cloud.core.utils;

import java.security.MessageDigest;
import java.util.Map;
import cn.javabook.cloud.core.parent.BaseUtil;

/**
 * 加密工具类
 * 
 */
public class MD5Util extends BaseUtil {
	private static final long serialVersionUID = -6296087060756739610L;

	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 获取MD5加密
	 *
	 */
	public static String md5encode(String input, String encoding) {
		String resultString = null;
		try {
			resultString = new String(input);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (encoding == null || "".equals(encoding)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(encoding)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	private static String md5encode(String inStr) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String sign(Map<String, String> params) {
		java.util.SortedMap<String, String> sortedParamMap = new java.util.TreeMap<>(params);
		String canonicalQS = canonicalize(sortedParamMap);
		String md5 = md5encode(canonicalQS).substring(9, 25);
		String time = params.get("timestamp");
		return md5encode(md5 + time).substring(9, 25);
	}

	private static String canonicalize(java.util.SortedMap<String, String> sortedParamMap) {
		if (sortedParamMap.isEmpty()) {
			return "";
		}

		StringBuilder buffer = new StringBuilder();
		java.util.Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> kvpair = iter.next();
			buffer.append(percentEncodeRfc3986(kvpair.getKey()));
			buffer.append("=");
			buffer.append(percentEncodeRfc3986(kvpair.getValue()));
			if (iter.hasNext()) {
				buffer.append("&");
			}
		}
		return buffer.toString();
	}

	private static String percentEncodeRfc3986(String s) {
		String out;
		if (s == null) {
			return "";
		}

		try {
			out = java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20").replace("*", "%2A");
		} catch (java.io.UnsupportedEncodingException e) {
			out = s;
		}

		return out;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuilder resultSb = new StringBuilder();
		for (byte value : b) {
			resultSb.append(byteToHexString(value));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
