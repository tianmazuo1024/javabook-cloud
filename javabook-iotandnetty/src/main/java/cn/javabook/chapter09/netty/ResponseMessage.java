package cn.javabook.chapter09.netty;

import java.util.List;

/**
 * 响应消息，和协议对应的实体类
 *
 */
public class ResponseMessage extends AbstractMessage {
	private static final long serialVersionUID = -3729488688473955307L;

	private String term;

	private String version;

	private String encry;

	private String time;

	public ResponseMessage(RequestMessage requestMessage) {
		this.setTerm(requestMessage.getTerm());
		this.setVersion(requestMessage.getVersion());
		this.setEncry(requestMessage.getEncry());
		this.setImei(requestMessage.getImei());
		this.setCid(requestMessage.getCid());
	}

	public ResponseMessage(String term, String version, String encry, String imei, String cid, String cmd, String time, List<String> args) {
		this.term = term;
		this.version = version;
		this.encry = encry;
		this.imei = imei;
		this.cid = cid;
		this.cmd = cmd;
		this.time = time;
		this.args = args;
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
