package cn.javabook.chapter09.netty;

/**
 * 请求消息，和协议对应的实体类
 *
 */
public class RequestMessage extends AbstractMessage {
	private static final long serialVersionUID = 6864066818381823897L;

	private String term;

	private String version;

	private String encry;

	private String time;

	public RequestMessage() {
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncry() {
		return encry;
	}

	public void setEncry(String encry) {
		this.encry = encry;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("{\"term\":\"%s\", \"version\":\"%s\", \"encry\":\"%s\", \"imei\":\"%s\", " +
						"\"cid\":\"%s\", \"cmd\":\"%s\", \"time\":\"%s\", \"args\":\"%s\"}",
				term, version, encry, imei, cid, cmd, time, args);
	}
}
