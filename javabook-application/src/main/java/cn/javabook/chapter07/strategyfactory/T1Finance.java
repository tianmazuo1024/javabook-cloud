package cn.javabook.chapter07.strategyfactory;

import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.common.Order;

/**
 * T+1结算策略
 * 
 */
public class T1Finance implements Finance {
	@Override
	public void settleAccount(Account account, Order order) {
		account.setBalance(account.getBalance() + 0.001 * order.getAmount());
		System.out.println("账户已变更，T+1返利：" + 0.001 * order.getAmount() + " 元");
	}
}
