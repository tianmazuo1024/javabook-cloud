package cn.javabook.chapter07.strategyfactory;

import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.common.Order;

/**
 * T+7结算策略
 * 
 */
public class T7Finance implements Finance {
	@Override
	public void settleAccount(Account account, Order order) {
		account.setBalance(account.getBalance() + 0.003 * order.getAmount());
		System.out.println("账户已变更，T+7返利：" + 0.003 * order.getAmount() + " 元");
	}
}
