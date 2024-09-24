package cn.javabook.chapter07.chain;

/**
 * 由风控部门审核的请求
 *
 */
public class RiskDeptHandler extends WithdrawHandler {
	@Override
	public void handle(double amount) {
		if (amount <= 10000) {
			System.out.println("风控部门已同意您的：" + amount + " 元提现请求");
		} else {
			if (null != getNext()) {
				getNext().handle(amount);
			} else {
				System.out.println("您的提现金额太大，无法满足您的亲求");
			}
		}
	}
}
