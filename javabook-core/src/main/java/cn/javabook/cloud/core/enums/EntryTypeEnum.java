package cn.javabook.cloud.core.enums;

/**
 * 会计分录类型枚举
 * 
 */
public enum EntryTypeEnum {
	BALANCE_PAY(10100, "余额支付"),
	FAST_PAY(10101, "快捷支付"),
	NET_B2C_PAY(10102, "B2C网银支付"),
	NET_B2B_PAY(10103, "B2B网银支付"),
	POS_PAY(10104, "POS消费");

	private int value;
	private String desc;

	private EntryTypeEnum(int value, String desc) {
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
