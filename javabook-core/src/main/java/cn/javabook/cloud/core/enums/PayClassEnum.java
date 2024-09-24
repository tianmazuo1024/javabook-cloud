package cn.javabook.cloud.core.enums;

/**
 * 支付订单类型枚举
 * 
 */
public enum PayClassEnum {
	CONSUME(10000, "消费"),
	RECHARGE(10001, "充值"),
	WITHDRAW(10002, "提现");

	private int value;
	private String desc;

	private PayClassEnum(int value, String desc) {
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
