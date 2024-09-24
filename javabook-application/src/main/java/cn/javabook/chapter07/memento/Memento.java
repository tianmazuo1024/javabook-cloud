package cn.javabook.chapter07.memento;

/**
 * 备忘录
 * 
 */
public class Memento {
	private String snapshot = "";

	public Memento(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
}
