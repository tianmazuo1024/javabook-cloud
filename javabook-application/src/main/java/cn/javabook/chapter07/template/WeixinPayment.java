package cn.javabook.chapter07.template;

import cn.javabook.chapter07.strategy.PayByWeixin;

/**
 * 微信支付类
 *
 */
public class WeixinPayment extends Payment {
    /**
     * 构造器
     *
     * @param strategy
     */
    public WeixinPayment(PayByWeixin strategy) {
        this.strategy = strategy;
    }

    /**
     * 支付前锁定账户
     *
     */
    @Override
    protected void payBefore() {
        System.out.println("payBefore：微信支付前处理");
    }

    /**
     * 支付后增加积分
     *
     */
    @Override
    protected void payAfter() {
        System.out.println("payAfter：微信支付后处理");
    }
}
