package cn.javabook.chapter07.memento;

/**
 * 发起备忘录
 * 
 */
public class Originator {
	private String snapshot = "";

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * 创建备忘录
	 *
	 * @return
	 */
	public Memento createMemento() {
		return new Memento(snapshot);
	}

	/**
	 * 恢复备忘录
	 *
	 * @param memento
	 */
	public void restoreMemento(Memento memento) {
		this.setSnapshot(memento.getSnapshot());
	}
}
