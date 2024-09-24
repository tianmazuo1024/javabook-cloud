package cn.javabook.cloud.core.enums;

/**
 * 支付方式枚举
 * 
 */
public enum PayTypeEnum {
	BALANCE(10000, "余额"),
	ALIPAY(10001, "支付宝"),
	WEIXIN(10002, "微信支付");

	private int value;
	private String desc;

	private PayTypeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
