package cn.javabook.chapter09.netty;

import io.netty.channel.ChannelHandlerContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 业务执行器
 *
 */
public class CommandDispatcher implements Runnable {
    public static final Map<String, ChannelHandlerContext> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void run() {
    }
}
