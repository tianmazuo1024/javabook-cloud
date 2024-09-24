package cn.javabook.chapter07.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车实现
 *
 */
public class ConcreteCart implements Cart {
	private final List<Product> list = new ArrayList<>();

	@Override
	public void add(Product product) {
		list.add(product);
	}

	@Override
	public void remove(Product product) {
		list.remove(product);
	}

	@Override
	public Iterator createIterator() {
		return(new ConcreteIterator(list));
	}
}
