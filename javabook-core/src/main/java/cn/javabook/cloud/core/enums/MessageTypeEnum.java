package cn.javabook.cloud.core.enums;

/**
 * 消息类型枚举
 * 
 */
public enum MessageTypeEnum {
	TRANSACTION(10000, "事务消息"),
	SERVICE(10001, "业务消息"),
	PUSH(10002, "推送消息"),
	USER(10003, "用户消息");

	private int value;
	private String desc;

	private MessageTypeEnum(int value, String desc) {
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
