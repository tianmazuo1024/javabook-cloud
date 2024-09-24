package cn.javabook.chapter09.netty;

import java.io.Serializable;
import java.util.List;

/**
 * 抽象消息类
 *
 */
public abstract class AbstractMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String imei;

    protected String cmd;

    protected String cid;

    protected List<String> args;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return String.format("{\"imei\":\"%s\", \"cmd\":\"%s\", \"args\":\"%s\"}", imei, cmd, args);
    }
}
