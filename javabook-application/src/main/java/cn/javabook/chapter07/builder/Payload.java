package cn.javabook.chapter07.builder;

/**
 * 支付接口的设置
 * 
 */
public class Payload {
	/**
	 * 默认构造器
	 */
	public Payload() {
	}

	/**
	 * 有生成器参数的私有构造器
	 */
	private Payload(Builder builder) {
		this.channel = builder.channel;
		this.body = builder.body;
		this.charge = builder.charge;
		this.tradeNo = builder.tradeNo;
		this.notifyUrl = builder.notifyUrl;
	}

	/**
	 * 内部生成器
	 */
	public static class Builder {
		private CHANNEL channel;
		private String body;
		private double charge;
		private String tradeNo;
		private String notifyUrl;

		public Builder() {
		}

		public Builder(CHANNEL channel, String body, double charge, String tradeNo, String notifyUrl) {
			this.channel = channel;
			this.body = body;
			this.charge = charge;
			this.tradeNo = tradeNo;
			this.notifyUrl = notifyUrl;
		}

		public Builder setChannel(CHANNEL channel) {
			this.channel = channel;
			return this;
		}

		public Builder setBody(String body) {
			this.body = body;
			return this;
		}

		public Builder setCharge(double charge) {
			this.charge = charge;
			return this;
		}

		public Builder setTradeNo(String tradeNo) {
			this.tradeNo = tradeNo;
			return this;
		}

		public Builder setNotifyUrl(String notifyUrl) {
			this.notifyUrl = notifyUrl;
			return this;
		}

		// 最终构建
		public Payload build() {
			return new Payload(this);
		}
	}

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
