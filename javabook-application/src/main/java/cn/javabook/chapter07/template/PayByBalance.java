package cn.javabook.chapter07.template;

import cn.javabook.chapter07.builder.Payload;
import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.strategy.PayStrategy;

/**
 * 余额支付
 *
 */
public class PayByBalance implements PayStrategy {
    private Account account;

    @Override
    public boolean pay(double amount) {
        account = new Account();
        // 余额支付设置
        System.out.println("<<<<<<<<<< 余额支付设置 >>>>>>>>>>");
        Payload.Builder builder = new Payload.Builder();
        Payload payload = builder.setChannel(Payload.CHANNEL.ACCOUNT)
                // 无手续费
                .setCharge(0.0)
                .setNotifyUrl("http://localhost:9090")
                .setBody("订单备注")
                .setTradeNo("202001230258863496515")
                .build();
        // 如果可用余额不足
        if (account.getBalance() < amount) {
            double total = account.getDeposit() + account.getBalance();
            // 如果押金加上可用余额，仍然不足
            if (total < amount) {
                return false;
            } else {
                // 先扣除全部可用余额
                double left = amount - account.getBalance();
                account.setBalance(0);
                // 扣除押金
                account.setDeposit(account.getDeposit() - left);
            }
        } else {
            // 从可用余额中扣除
            account.setBalance(account.getBalance() - amount);
        }
        return true;
    }
}
