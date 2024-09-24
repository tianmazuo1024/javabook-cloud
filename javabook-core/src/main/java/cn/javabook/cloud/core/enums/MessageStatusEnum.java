package cn.javabook.cloud.core.enums;

/**
 * 事务消息状态枚举
 * 
 */
public enum MessageStatusEnum {
	WAITING(10000, "待确认"),
	SENDING(10001, "发送中"),
	RESENDING(10002, "重新发送"),
	CONFIRM(10003, "已确认"),
	REMOVED(10004, "已删除");

	private int value;
	private String desc;

	private MessageStatusEnum(int value, String desc) {
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
