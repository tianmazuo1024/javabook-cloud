package cn.javabook.chapter09.netty;

import io.netty.channel.*;
import io.netty.util.AttributeKey;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 监听ChannelHandler事件
 *
 */
public class ChannelListener extends ChannelHandlerAdapter {
    // 创建AttributeKey常量池，同时初始化ConcurrentHashMap，key="channelInfo"
    public static final AttributeKey<ChannelInfo> CHANNEL_INFO = AttributeKey.valueOf("channelInfo");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 监听pipeline队尾的Handler事件，即自定义的业务处理器ServerHandler
        ChannelPipeline pipeline = ctx.pipeline();
        Iterator<Entry<String, ChannelHandler>> iterator = pipeline.iterator();
        ChannelHandlerContext context = null;
        // 一直找到队尾的ChannelHandler
        while (iterator.hasNext()) {
            Entry<String, ChannelHandler> entry = iterator.next();
            context = pipeline.context(entry.getValue());
        }
        // 保存每个连接到服务端的终端信息，后面的get()之所以能得到数据，就是在这里「塞」进去的
        ctx.channel().attr(CHANNEL_INFO).set(new ChannelInfo(context));
        super.channelActive(ctx);
    }
}
