package cn.javabook.chapter07.state.news;

/**
 * 订单状态抽象类
 * 
 */
public abstract class State {
	protected final Context context;

	public State(Context context) {
		this.context = context;
	}

	/**
	 * 下单
	 */
	public abstract String onOrdered();

	/**
	 * 取消订单
	 */
	public abstract String onCancel();

	/**
	 * 订单支付
	 */
	public abstract String onPaid();

	/**
	 * 确认收货
	 */
	public abstract String onConfirm();
}
