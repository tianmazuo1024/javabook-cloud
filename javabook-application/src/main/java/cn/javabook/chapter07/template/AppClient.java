package cn.javabook.chapter07.template;

import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.common.Order;
import cn.javabook.chapter07.strategy.PayByBalance;
import cn.javabook.chapter07.strategy.PayByAlipay;
import cn.javabook.chapter07.strategy.PayByWeixin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 客户端应用
 * 
 */
public class AppClient {
	// 获得键盘输入
	private static String getInput() {
		String str = "";
		try {
			str = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// 初始化一个账户
	private static Account initAccount() {
		Account account = new Account();
		account.setAccid("1");
		account.setDeposit(500);
		account.setBalance(200);
		return account;
	}

	// 创建一笔订单
	private static Order createOrder() {
		Order order = new Order();
		System.out.print("请输入交易编号：");
		order.setOid(getInput());
		System.out.print("请输入交易金额：");
		order.setAmount(Integer.parseInt(getInput()));
		// 返回订单
		return order;
	}

	// 支付
	private static void pay(double amount) {
		System.out.print("请选择支付类型：");
		String type = getInput();
		switch (type) {
			case "alipay":
				AlipayPayment alipayPayment = new AlipayPayment(new PayByAlipay());
				alipayPayment.templateMethod(amount);
				break;
			case "weixin":
				WeixinPayment weixinPayment = new WeixinPayment(new PayByWeixin());
				weixinPayment.templateMethod(amount);
				break;
			case "yue":// 余额支付
				BalancePayment balancePayment = new BalancePayment(new PayByBalance());
				balancePayment.templateMethod(amount);
				break;
			default:
				break;
		}
	}

	// 打印出当前卡内交易余额
	private static void showAccount(Account account, int amount) {
		System.out.println("账户编号:" + account.getAccid());
		System.out.println("账户押金：" + account.getDeposit() + " 元");
		System.out.println("账户余额：" + (account.getBalance() - amount) + " 元");
	}

	// 模拟交易
	public static void main(String[] args) {
		Account account = initAccount();
		System.out.println("\n======== 初始化账户信息 =========");
		showAccount(account, 0);
		System.out.println();

		// 创建一笔订单
		Order order = createOrder();
		// 支付
		pay(order.getAmount());

		// 交易成功，打印出成功处理消息
		System.out.println("\n========= 订单凭证 =========");
		System.out.println(order.getOid() + " 订单交易成功！");
		System.out.println("本次支付的订单金额为：" + order.getAmount() + " 元");

		// 看看账户余额
		System.out.println("\n======== 当前账户信息 =========");
		showAccount(account, 100);
	}
}
