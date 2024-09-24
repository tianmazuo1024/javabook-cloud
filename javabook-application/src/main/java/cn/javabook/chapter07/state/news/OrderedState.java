package cn.javabook.chapter07.state.news;

/**
 * 已下单
 * 
 */
public class OrderedState extends State {
	public OrderedState(Context context) {
		super(context);
	}

	@Override
	public String onOrdered() {
		if (context.isOrdered()) {
			System.out.println("已下单，不能重复下单");
			return "已下单，不能重复下单";
		}
		System.out.println("已下单");
		context.setOrdered(true);
		return "已下单";
	}

	@Override
	public String onCancel() {
		if (context.isOrdered()) {
			System.out.println("此订单已取消");
			// 状态修改
			context.changeState(new CancelState(context));
			return "此订单已取消";
		}
		System.out.println("还未下单，无法取消");
		return "还未下单，无法取消";
	}

	@Override
	public String onPaid() {
		if (context.isOrdered()) {
			System.out.println("此订单已付款");
			// 状态修改
			context.changeState(new PaidState(context));
			return "此订单已付款";
		}
		System.out.println("还未下单，无法付款");
		return "还未下单，无法付款";
	}

	@Override
	public String onConfirm() {
		if (context.isOrdered()) {
			System.out.println("已下单还未付款，无法确认收货");
			return "已下单还未付款，无法确认收货";
		}
		return "还未下单，请先下单";
	}
}
