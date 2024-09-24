package cn.javabook.chapter09.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 自定义空闲处理器
 *
 */
public final class IdleConnectionHandler extends ChannelHandlerAdapter {
    public void userEventTriggered(ChannelHandlerContext ctx, Object event) {
        ChannelInfo channelInfo = ctx.channel().attr(ChannelListener.CHANNEL_INFO).get();
        String imei = channelInfo.getImei();
        if (event instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) event;
            switch (e.state()) {
                case WRITER_IDLE:
                    System.out.println(imei + " 写空闲");
                    break;
                case READER_IDLE:
                    System.out.println(imei + " 读空闲");
                    break;
                case ALL_IDLE:
                    System.out.println(imei + " 读写都空闲");
                    break;
                default:
                    break;
            }
        }
        ChannelFuture future = ctx.close();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture aFuture) {
                TerminalManager terminalManager = TerminalManager.getInstance();
                if (imei != null) {
                    terminalManager.logout(channelInfo);
                }
                System.out.println("服务端断开和 " + imei + " 的连接");
            }
        });
    }
}