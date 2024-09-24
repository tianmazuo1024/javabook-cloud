package cn.javabook.chapter07.chain;

/**
 * 由公司副总裁审核的请求
 *
 */
public class ViceHandler extends WithdrawHandler {
	@Override
	public void handle(double amount) {
		if (amount <= 100000) {
			System.out.println("公司副总裁已同意您的：" + amount + " 元提现请求");
		} else {
			if (null != getNext()) {
				getNext().handle(amount);
			} else {
				System.out.println("您的提现金额太大，无法满足您的亲求");
			}
		}
	}
}
