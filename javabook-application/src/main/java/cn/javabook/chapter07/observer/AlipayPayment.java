package cn.javabook.chapter07.observer;

import cn.javabook.chapter07.strategy.PayByAlipay;

/**
 * 支付宝支付类
 *
 */
public class AlipayPayment extends Payment {
    /**
     * 构造器
     *
     * @param strategy
     */
    public AlipayPayment(PayByAlipay strategy) {
        this.strategy = strategy;
    }

    /**
     * 支付前锁定账户
     *
     */
    @Override
    protected void payBefore() {
        System.out.println("payBefore：支付宝支付前处理");
    }

    /**
     * 支付后增加积分
     *
     */
    @Override
    protected void payAfter() {
        System.out.println("payAfter：支付宝支付后处理");
    }
}
