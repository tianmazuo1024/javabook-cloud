package cn.javabook.chapter07.component;

import java.util.ArrayList;
import java.util.List;

/**
 * 子订单
 *
 */
public class SubOrder implements OrderAction {
	/**
	 * 子订单名字
	 */
	private String name = "";
	private List<OrderAction> suborder = new ArrayList<>();

	public SubOrder(String name) {
		this.name = name;
	}

	public void add(OrderAction things) {
		suborder.add(things);
	}

	/**
	 * 计算价格
	 *
	 */
	@Override
	public double calculation() {
		double total = 0;
		for (OrderAction action : suborder) {
			total += action.calculation();
		}
		return total;
	}

	/**
	 * 打印小票
	 *
	 */
	@Override
	public void receipt() {
		for (OrderAction action : suborder) {
			action.receipt();
		}
	}
}
