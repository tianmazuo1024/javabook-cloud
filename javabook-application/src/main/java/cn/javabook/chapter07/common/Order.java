package cn.javabook.chapter07.common;

/**
 * 用户订单
 * 
 */
public class Order {
	// 订单编码
	private String oid = "";

	// 订单金额
	private double amount = 0;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format("{\"oid\":\"%s\", \"amount\":%f}", oid, amount);
	}
}
