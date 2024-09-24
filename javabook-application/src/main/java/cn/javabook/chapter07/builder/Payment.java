package cn.javabook.chapter07.builder;

import cn.javabook.chapter07.common.Account;
import cn.javabook.chapter07.common.Order;

/**
 * 支付类
 *
 */
public class Payment {
    private Payload payload;

    /**
     * 支付方法（使用生成器模式改造）
     */
    protected boolean pay(int type, final Account account, final Order order) {
        // 支付接口的设置
        Payload.Builder builder = new Payload.Builder();

        // 如果是支付宝
        if (Payload.CHANNEL.ALIPAY.ordinal() == type) {
            builder = builder.setChannel(Payload.CHANNEL.ALIPAY)
                            // 千六手续费
                            .setCharge(order.getAmount() * 0.006)
                            .setNotifyUrl("http://localhost:7070");
        }
        // 如果是微信
        if (Payload.CHANNEL.WEIXIN.ordinal() == type) {
            builder = builder.setChannel(Payload.CHANNEL.WEIXIN)
                    // 千六手续费
                    .setCharge(order.getAmount() * 0.006)
                    .setNotifyUrl("http://localhost:8080");
        }
        // 如果是余额
        if (Payload.CHANNEL.ALIPAY.ordinal() == type) {
            builder = builder.setChannel(Payload.CHANNEL.ACCOUNT)
                    // 无手续费
                    .setCharge(0.0)
                    .setNotifyUrl("http://localhost:9090");
        }
        payload = builder.setBody("订单备注")
                        .setTradeNo("202001230258863496515")
                        .build();
        System.out.println("您需要支付的金额是：" + order.getAmount());

        // 用余额支付
        if (payload.getChannel() == Payload.CHANNEL.ACCOUNT) {
            // 如果可用余额不足
            if (account.getBalance() < order.getAmount()) {
                return false;
            } else {
                // 从可用余额中扣除
                account.setBalance(account.getBalance() - order.getAmount());
            }
        }
        return true;
    }
}
