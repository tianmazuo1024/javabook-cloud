package cn.javabook.chapter07.state.news;

/**
 * 确认收货
 *
 */
public class ConfirmState extends State {
	public ConfirmState(Context context) {
		super(context);
	}

	@Override
	public String onOrdered() {
		System.out.println("此订单已确认收货，不能重复下单");
		return "此订单已确认收货，不能重复下单";
	}

	@Override
	public String onCancel() {
		System.out.println("此订单已确认收货，不能取消");
		return "此订单已确认收货，不能取消";
	}

	@Override
	public String onPaid() {
		System.out.println("此订单已确认收货，不能重复支付");
		return "此订单已确认收货，不能重复支付";
	}

	@Override
	public String onConfirm() {
		System.out.println("此订单已确认收货，不能重复确认");
		return "此订单已确认收货，不能重复确认";
	}
}
