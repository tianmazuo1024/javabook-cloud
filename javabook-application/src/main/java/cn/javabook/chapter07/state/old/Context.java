package cn.javabook.chapter07.state.old;

/**
 * 订单状态
 * 
 */
public class Context implements State {
	private int state;

	@Override
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 下单
	 */
	@Override
	public void order() {
		switch (this.state) {
			case ORDERED:
				System.out.println("已经是下单状态");
				break;
			case CANCELED:
				System.out.println("已取消的订单，不能下单");
				break;
			case PAID:
				System.out.println("已支付的订单，不能下单");
				break;
			case CONFIRM:
				System.out.println("已确认收货，不能下单");
				break;
		}
	}

	/**
	 * 取消订单
	 */
	@Override
	public void cancel() {
		switch (this.state) {
			// 取消订单
			case ORDERED:
				this.setState(CANCELED);
				System.out.println("订单取消了");
				break;
			case CANCELED:
				System.out.println("已取消订单，不能再取消");
				break;
			case PAID:
				System.out.println("已支付订单，不能取消");
				break;
			case CONFIRM:
				System.out.println("已确认收货，不能取消");
				break;
		}
	}

	/**
	 * 订单支付
	 */
	@Override
	public void paid() {
		switch (this.state) {
			// 订单付款
			case ORDERED:
				this.setState(PAID);
				System.out.println("订单付款了");
				break;
			case CANCELED:
				System.out.println("已取消订单，无法支付");
				break;
			case PAID:
				System.out.println("已支付订单，不能重复支付");
				break;
			case CONFIRM:
				System.out.println("已确认收货，无法支付");
				break;
		}
	}

	/**
	 * 确认收货
	 */
	@Override
	public void confirm() {
		switch (this.state) {
			case ORDERED:
				System.out.println("已下单但还未付款，不能直接完成");
				break;
			case CANCELED:
				System.out.println("已取消订单，无法完成");
				break;
			// 完成订单
			case PAID:
				this.setState(CONFIRM);
				System.out.println("订单完成了");
				break;
			case CONFIRM:
				System.out.println("已确认收货，不能再次完成");
				break;
		}
	}

	public static void main(String[] args) {
		Context context = new Context();
		// 设置订单初始状态
		context.setState(State.ORDERED);
		// 订单支付
		context.order();
		// 取消订单
		context.cancel();
		// 订单支付
		context.paid();
		// 确认收货
		context.confirm();
	}
}
