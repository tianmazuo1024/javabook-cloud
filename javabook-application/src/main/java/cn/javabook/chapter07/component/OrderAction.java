package cn.javabook.chapter07.component;

/**
 * 订单行为接口
 *
 */
public interface OrderAction {
	/**
	 * 计算价格
	 *
	 * @return
	 */
	public double calculation();

	/**
	 * 打印小票
	 *
	 */
	public void receipt();
}
