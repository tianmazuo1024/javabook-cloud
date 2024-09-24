package cn.javabook.cloud.core.enums;

/**
 * 事务消息是否死亡枚举
 * 
 */
public enum MessageDeadEnum {
	ACTIVE(10000, "存活中"),
	DEAD(10001, "已死亡");

	private int value;
	private String desc;

	private MessageDeadEnum(int value, String desc) {
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
