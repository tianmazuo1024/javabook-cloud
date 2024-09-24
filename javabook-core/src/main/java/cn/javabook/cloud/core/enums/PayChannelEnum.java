package cn.javabook.cloud.core.enums;

/**
 * 来源系统类型枚举
 * 
 */
public enum PayChannelEnum {
	APP(10000, "APP支付"),
	WEB(10001, "WEB支付");

	private int value;
	private String desc;

	private PayChannelEnum(int value, String desc) {
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
