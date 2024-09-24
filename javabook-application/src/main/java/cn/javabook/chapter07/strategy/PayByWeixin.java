package cn.javabook.chapter07.strategy;

import cn.javabook.chapter07.builder.Payload;

/**
 * 用微信支付
 *
 */
public class PayByWeixin implements PayStrategy {
    @Override
    public boolean pay(double amount) {
        // 微信支付接口设置
        System.out.println("<<<<<<<<<< 微信支付接口设置 >>>>>>>>>>");
        Payload.Builder builder = new Payload.Builder();
        Payload payload = builder.setChannel(Payload.CHANNEL.WEIXIN)
                // 千六手续费
                .setCharge(amount * 0.006)
                .setNotifyUrl("http://localhost:8080")
                .setBody("订单备注")
                .setTradeNo("202001230258863496515")
                .build();
        // TODO：调用微信SDK
        return true;
    }
}
