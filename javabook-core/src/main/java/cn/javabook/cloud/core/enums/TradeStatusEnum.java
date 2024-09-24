package cn.javabook.cloud.core.enums;

/**
 * 交易状态类型枚举
 * 
 */
public enum TradeStatusEnum {
	CREATED(10000, "订单已创建"),
	WAITING_PAYMENT(10001, "等待支付"),
	WAITING_WITHDRAW(10002, "等待提现"),
	PAYMENT_SUCCESS(10003, "支付成功"),
	WITHDRAW_SUCCESS(10004, "提现成功"),
	SUCCESS(10005, "支付完成"),
	TRADE_SUCCESS(10006, "交易成功"),
	FAIL(10007, "交易失败"),
	CANCELED(10008, "订单已取消"),
	TIMEOUTED(10009, "订单已超时");

	private int value;
	private String desc;

	private TradeStatusEnum(int value, String desc) {
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
