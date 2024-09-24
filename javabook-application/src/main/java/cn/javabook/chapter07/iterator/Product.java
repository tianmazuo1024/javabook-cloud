package cn.javabook.chapter07.iterator;

/**
 * 用户所购商品
 *
 */
public class Product {
	private String name;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 类别，后继可以依据类别来决定是否抽检
	 */
	private String type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("{\"name\":\"%s\", \"quantity\":\"%d\", \"price\":%f}", name, quantity, price);
	}
}
