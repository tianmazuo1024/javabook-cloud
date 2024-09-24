package cn.javabook.chapter07.iterator;

import java.util.List;

/**
 * 抽检实现类
 *
 */
public class ConcreteIterator implements Iterator {
	private List<Product> list = null;
	private int index = -1;

	public ConcreteIterator(List<Product> list) {
		this.list = list;
	}

	@Override
	public Product getFirst() {
		index = 0;
		return list.get(index);
	}

	@Override
	public Product getNext() {
		Product product = null;
		if (this.hasNext()) {
			product = list.get(++index);
		}
		return product;
	}

	@Override
	public boolean hasNext() {
		return index < list.size() - 1;
	}
}
