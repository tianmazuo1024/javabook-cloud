package cn.javabook.chapter08.otp;

/**
 * 测试动态令牌
 * 
 */
public class OTPTester {
	public static void main(String[] args) {
//		/*
//		 * OTP算法的伪代码
//		 */
//		// 生成一个64位的随机字符串
//		String original_secret = GenerateOriginalSecret(64);
//		// 将这个字符串去除空格、转大写并且使用Base32工具解码
//		String secret = Base32_Decode(To_UpperCase(Rmove_Space(original_secret)));
//		// 将解码后的字符串用十六进制编码器编码
//		String secret_hex = HexEncoding.encode(secret);
//		// 将编码后的十六进制字符串转换为字节数组
//		byte[] k = Hex_String_To_Bytes(secret_hex);
//		// 在TOTP中的c是使用当前Unix时间戳减去初始计数时间戳，然后除以时间窗口（也就是有效期）而获得的。"30"表示30秒内有效
//		byte[] totp_c = Hex_String_To_Bytes(Long.toHexString(Current_Time / 30).toUpperCase());
//		// 在HOTP中的c是一个由随机数转换而成的字节数组
//		byte[] hotp_c = GenerateRamdomNumber();
//		// 使用SHA1算法对HMAC哈希运算消息认证码进行签名认证，参数为k和c
//		byte[] hash = Hmac_Sha1(k, c);
//		// 将签名认证所得结果的字节数组的最后一位和十六进制数0xf执行按位与运算获得偏移量
//		int offset = hash[hash.length - 1] & 0xf;
//		// 按偏移量对hash数组进行左移
//		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
//		// 运算后取模所得整数再转字符串，其结果就是6位数的二次验证码
//		String otp = Integer.toString(binary % 1000000);

		// 用OTPAuthUtil.generateSecret(64)创建密钥secret信息，测试时须将此密钥保存下来
		String secret = "WJ5E332WQQQ6HHUPM2JELL2ZCFNK56MQLIYD7RY4K5NNTHO6TURA";
		// 验证账户（可填写自己的名字，不要用中文）
		String account = "javabook";
		// 创建协议URI
		String protocaluri = OTPAuthUtil.generateTotpURI(account, secret);
		System.out.println(protocaluri);
		// 验证生成的动态口令是否正确
		String code = "[这里输入APP或小程序生成的动态验证码]";
		System.out.println("动态验证码是否正确：" + OTPAuthUtil.verify(secret, code));
	}
}
