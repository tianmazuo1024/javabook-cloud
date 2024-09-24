package cn.javabook.chapter09.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * 维护终端和连接之间的会话，同时也以线程安全的方式向外发送消息
 *
 */
public class ChannelInfo {
    // 给每个终端分配唯一的IMEI号
    private String imei;

    // ChannelHandlerContext是终端本身的处理器上下文，与IMEI无关
    private final ChannelHandlerContext context;

    public ChannelInfo(ChannelHandlerContext context) {
        this.context = context;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public ChannelHandlerContext getContext() {
        return this.context;
    }
}
