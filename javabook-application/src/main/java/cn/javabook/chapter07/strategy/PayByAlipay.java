package cn.javabook.chapter07.strategy;

import cn.javabook.chapter07.builder.Payload;

/**
 * 支付宝支付
 *
 */
public class PayByAlipay implements PayStrategy {
    @Override
    public boolean pay(double amount) {
        // 支付宝接口设置
        System.out.println("<<<<<<<<<< 支付宝接口设置 >>>>>>>>>>");
        Payload.Builder builder = new Payload.Builder();
        Payload payload = builder.setChannel(Payload.CHANNEL.ALIPAY)
                // 千六手续费
                .setCharge(amount * 0.006)
                .setNotifyUrl("http://localhost:7070")
                .setBody("订单备注")
                .setTradeNo("202001230258863496515")
                .build();
        // TODO：调用支付宝SDK
        return true;
    }
}
