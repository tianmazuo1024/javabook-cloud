package cn.javabook.chapter07.strategy;

import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.builder.Payload;

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
            return false;
        } else {
            // 从可用余额中扣除
            account.setBalance(account.getBalance() - amount);
        }
        return true;
    }
}
