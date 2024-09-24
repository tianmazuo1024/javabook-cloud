package cn.javabook.chapter07.iterator;

/**
 * 抽检接口
 *
 */
public interface Iterator {
	/**
	 * 获取第一个
	 *
	 * @return
	 */
	public Product getFirst();

	/**
	 * 获取下一个
	 *
	 * @return
	 */
	public Product getNext();

	/**
	 * 是否还有后继节点
	 *
	 * @return
	 */
	public boolean hasNext();
}
