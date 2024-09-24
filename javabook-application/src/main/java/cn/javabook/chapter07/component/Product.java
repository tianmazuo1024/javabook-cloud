package cn.javabook.chapter07.component;

/**
 * 用户所购商品
 *
 */
public class Product implements OrderAction {
	private String name;
	private int quantity;
	private double price;

	public Product(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("{\"name\":\"%s\", \"quantity\":%d, \"price\":%.2f}", name, quantity, price);
	}

	@Override
	public double calculation() {
		return price * quantity;
	}

	@Override
	public void receipt() {
		System.out.println(name + "(数量：" + quantity + "，单价：" + price + "元)");
	}
}
