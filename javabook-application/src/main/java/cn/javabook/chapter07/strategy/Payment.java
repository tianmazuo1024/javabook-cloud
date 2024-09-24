package cn.javabook.chapter07.strategy;

/**
 * 支付类
 *
 */
public class Payment {
    protected PayStrategy strategy;

    /**
     * 支付方法：使用策略模式改造
     *
     */
    protected boolean pay(final double amount) {
        // 支付策略
        return strategy.pay(amount);
    }
}
