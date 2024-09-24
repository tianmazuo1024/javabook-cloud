package cn.javabook.cloud.core.enums;

/**
 * 交易对象类型枚举
 * 
 */
public enum ObjectTypeEnum {
	USER(10000, "用户"),
	STORE(10001, "商家"),
	PLATFORM(10002, "平台"),
	PARTNER(10003, "合作者"),
	THIRD(10004, "第三方");

	private int value;
	private String desc;

	private ObjectTypeEnum(int value, String desc) {
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
