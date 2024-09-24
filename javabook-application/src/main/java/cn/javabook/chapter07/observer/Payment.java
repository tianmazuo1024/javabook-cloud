package cn.javabook.chapter07.observer;

import cn.javabook.chapter07.strategy.PayStrategy;
import java.util.Vector;

/**
 * 支付抽象类，实现相关观察者接口
 *
 */
public abstract class Payment {
	protected PayStrategy strategy;

	// 订阅者列表
	protected Vector<Subscriber> subscribers = new Vector<>();

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

	/**
	 * 增加订阅者
	 *
	 * @param subscriber
	 */
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * 取消订阅者
	 *
	 * @param subscriber
	 */
	public void removeSubscriber(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	/**
	 * 通知所有订阅者
	 *
	 * @param channel
	 */
	public void notifySubscribers(String channel, int point) {
		for (Subscriber subscriber : subscribers) {
			subscriber.payUpdate(channel);
			subscriber.pointUpdate(channel, point);
		}
	}
}
