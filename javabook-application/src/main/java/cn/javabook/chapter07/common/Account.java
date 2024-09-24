package cn.javabook.chapter07.common;

import cn.javabook.chapter07.observer.Subscriber;

/**
 * 用户账户
 *
 */
public class Account implements Subscriber {
	// 账户编码
	private String accid = "";
	// 账户押金
	private double deposit = 0;
	// 账户余额
	private double balance = 0;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public void payUpdate(String channel) {
		System.out.println("使用 " + channel + " 支付成功");
	}

	@Override
	public void pointUpdate(String channel, int point) {
		System.out.println(channel + " 后积分增加 " + point + " 分");
	}

	@Override
	public String toString() {
		return String.format("{\"accid\":\"%s\", \"deposit\":%f, \"balance\":%f}", accid, deposit, balance);
	}
}
