package cn.javabook.chapter07.memento;

import java.util.LinkedList;

/**
 * 负责人
 * 
 */
public class Caretaker {
	private Originator originator;

	/**
	 * 快照列表
	 *
	 */
	private LinkedList<Memento> history = new LinkedList<>();

	public Caretaker(Originator originator) {
		this.originator = originator;
	}

	/**
	 * 存储备忘录
	 *
	 */
	public void save() {
		Memento memento = originator.createMemento();
		history.add(memento);
		System.out.println("创建备忘录并保存至历史快照列表");
	}

	/**
	 * 恢复备忘录
	 *
	 */
	public void undo() {
		if (0 < history.size()) {
			Memento memento = history.getLast();
			originator.restoreMemento(memento);
			history.removeLast();
		}
	}
}
