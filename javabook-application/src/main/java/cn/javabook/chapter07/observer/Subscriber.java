package cn.javabook.chapter07.observer;

/**
 * 订阅者接口
 *
 */
public interface Subscriber {
	/**
	 * 支付成功
	 */
	public void payUpdate(String channel);

	/**
	 * 积分更新
	 */
	public void pointUpdate(String channel, int point);
}
