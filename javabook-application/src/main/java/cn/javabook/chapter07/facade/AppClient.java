package cn.javabook.chapter07.facade;

import cn.javabook.chapter07.strategy.PayByBalance;
import cn.javabook.chapter07.common.Order;
import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.strategy.PayByAlipay;
import cn.javabook.chapter07.strategy.PayByWeixin;
import cn.javabook.chapter07.template.AlipayPayment;
import cn.javabook.chapter07.template.BalancePayment;
import cn.javabook.chapter07.template.WeixinPayment;
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
		// 增加提示，手动输入账户类型是为了处理结算模式
		System.out.println("请输入账户编码（请以t1或非t1开头）");
		// 这里将原来的account.setAccid("1")改为接收键盘输入的方式
		account.setAccid(getInput());
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
			case "yue":
				BalancePayment balancePayment = new BalancePayment(new PayByBalance());
				balancePayment.templateMethod(amount);
				break;
			default:
				break;
		}
	}

	// 打印出当前卡内交易余额
	private static void showAccount(Account account) {
		System.out.println("账户编号:" + account.getAccid());
		System.out.println("账户押金：" + account.getDeposit() + " 元");
		System.out.println("账户余额：" + account.getBalance() + " 元");
	}

	// 模拟交易
	public static void main(String[] args) {
		Account account = initAccount();
		System.out.println("\n======== 初始化账户信息 =========");
		showAccount(account);
		System.out.println();

		// 创建一笔订单
		Order order = createOrder();
		// 支付
		pay(order.getAmount());

		// 执行结算模式
		// if (account.getAccid().contains("t1")) {
		// 	new FinanceContext(new T1Finance()).settleAccount(account, order);
		// }
		// if (account.getAccid().contains("t7")) {
		// 	new FinanceContext(new T7Finance()).settleAccount(account, order);
		// }
		// 创建策略工厂
		// StrategyFactory factory = StrategyFactory.getInstance();
		// // 初始化
		// factory.init();
		// // 通过工厂获取一个具体的策略对象
		// Finance finance = factory.get(account.getAccid());
		// // 将策略对象包装进上下文中
		// FinanceContext oldContext = new FinanceContext(finance);
		// // 通过上下文执行结算处理
		// oldContext.settleAccount(account, order);
		// 调用外观模式
		FinanceFacade.doSettle(account, order);

		// 交易成功，打印出成功处理消息
		System.out.println("\n========= 订单凭证 =========");
		System.out.println(order.getOid() + " 订单交易成功！");
		System.out.println("本次支付的订单金额为：" + order.getAmount() + " 元");

		// 看看账户余额
		System.out.println("\n======== 当前账户信息 =========");
		showAccount(account);
	}
}
