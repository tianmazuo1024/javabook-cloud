package cn.javabook.chapter07.state.news;

/**
 * 订单付款
 *
 */
public class PaidState extends State {
	public PaidState(Context context) {
		super(context);
	}

	@Override
	public String onOrdered() {
		System.out.println("已付款，不能重复下单");
		return "已付款，不能重复下单";
	}

	@Override
	public String onCancel() {
		System.out.println("已付款，不能取消");
		return "已付款，不能重复下单";
	}

	@Override
	public String onPaid() {
		System.out.println("不能重复支付");
		return "不能重复支付";
	}

	@Override
	public String onConfirm() {
		System.out.println("确认收货");
		// 状态修改
		context.changeState(new ConfirmState(context));
		return "确认收货";
	}
}
