package cn.javabook.chapter07.chain;

/**
 * 提现抽象处理者角色
 *
 */
public abstract class WithdrawHandler {
	private WithdrawHandler next;

	public void setNext(WithdrawHandler next) {
		this.next = next;
	}

	public WithdrawHandler getNext() {
		return next;
	}

	// 处理请求的方法
	public abstract void handle(double amount);
}
