package cn.javabook.chapter07.state.news;

/**
 * 取消订单
 *
 */
public class CancelState extends State {
	public CancelState(Context context) {
		super(context);
	}

	@Override
	public String onOrdered() {
		System.out.println("此订单已取消");
		return "此订单已取消";
	}

	@Override
	public String onCancel() {
		System.out.println("此订单不能再次取消");
		return "此订单不能再次取消";
	}

	@Override
	public String onPaid() {
		System.out.println("此订单已取消，无法付款");
		return "此订单已取消，无法付款";
	}

	@Override
	public String onConfirm() {
		System.out.println("此订单已取消，不能确认收货");
		return "此订单已取消，不能确认收货";
	}
}
