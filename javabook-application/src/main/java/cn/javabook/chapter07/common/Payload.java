package cn.javabook.chapter07.common;

/**
 * 支付接口的设置
 * 
 */
public class Payload {
	public enum CHANNEL {
		// 余额支付
		ACCOUNT,
		// 支付宝支付
		ALIPAY,
		// 微信支付
		WEIXIN
	}

	// 支付渠道
	private CHANNEL channel;
	// 支付备注
	private String body;
	// 手续费
	private double charge;
	// 交易编码
	private String tradeNo;
	// 回调地址
	private String notifyUrl;

	public CHANNEL getChannel() {
		return channel;
	}

	public void setChannel(CHANNEL channel) {
		this.channel = channel;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String toString() {
		return String.format("{\"body\":\"%s\", "
				+ "\"charge\":%f, \"tradeNo\":\"%s\", "
				+ "\"notifyUrl\":\"%s\"}", body, 
				charge, tradeNo, notifyUrl);
	}
}
