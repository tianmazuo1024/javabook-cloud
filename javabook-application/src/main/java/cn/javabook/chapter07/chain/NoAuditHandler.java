package cn.javabook.chapter07.chain;

/**
 * 无需审核的请求
 *
 */
public class NoAuditHandler extends WithdrawHandler {
	@Override
	public void handle(double amount) {
		if (amount <= 2000) {
			System.out.println("您已提现：" + amount + " 元，无需审核");
		} else {
			if (null != getNext()) {
				getNext().handle(amount);
			} else {
				System.out.println("您的提现金额太大，无法满足您的亲求");
			}
		}
	}
}
