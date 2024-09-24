package cn.javabook.chapter07.template;

import cn.javabook.chapter07.strategy.PayStrategy;

/**
 * 支付类
 *
 */
public abstract class Payment {
    protected PayStrategy strategy;

    /**
     * 抽象方法：支付前锁定账户
     *
     */
    protected abstract void payBefore();

    /**
     * 抽象方法：支付后增加积分
     *
     */
    protected abstract void payAfter();

    /**
     * 模板方法
     *
     */
    public void templateMethod(final double amount) {
        // 支付前锁定账户
        payBefore();
        // 支付
        pay(amount);
        // 支付后增加积分
        payAfter();
    }

    /**
     * 支付方法：使用策略模式改造
     *
     */
    protected boolean pay(final double amount) {
        // 支付策略
        return strategy.pay(amount);
    }
}
