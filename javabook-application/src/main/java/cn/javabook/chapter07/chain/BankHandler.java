package cn.javabook.chapter07.chain;

/**
 * 由线下柜台当面审核的请求
 *
 */
public class BankHandler extends WithdrawHandler {
	@Override
	public void handle(double amount) {
		if (amount <= 1000000) {
			System.out.println("柜台已同意您的：" + amount + " 元提现请求");
		} else {
			if (null != getNext()) {
				getNext().handle(amount);
			} else {
				System.out.println("您的提现金额太大，无法满足您的请求");
			}
		}
	}
}
