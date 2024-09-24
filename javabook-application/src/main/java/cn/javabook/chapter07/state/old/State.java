package cn.javabook.chapter07.state.old;

/**
 * 状态接口
 * 
 */
public interface State {
	/**
	 * 已下单状态
	 */
	public static int ORDERED = 0;

	/**
	 * 已取消状态
	 */
	public static int CANCELED = 1;

	/**
	 * 已支付状态
	 */
	public static int PAID = 2;

	/**
	 * 确认收货
	 */
	public static int CONFIRM = 3;

	/**
	 * 设置订单状态
	 */
	public void setState(int state);

	/**
	 * 下单的方法
	 */
	public void order();

	/**
	 * 取消订单的方法
	 */
	public void cancel();

	/**
	 * 订单支付的方法
	 */
	public void paid();

	/**
	 * 确认收货的方法
	 */
	public void confirm();
}
