package cn.javabook.chapter09.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 负责处理读写事件的ChannelHandler
 *
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    // 处理读事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 普通的处理
        ByteBuf bb = (ByteBuf)msg;
        // 创建一个和buf同等长度的字节数组
        byte[] reqByte = new byte[bb.readableBytes()];
        // 将buf中的数据读取到数组中
        bb.readBytes(reqByte);
        String reqStr = new String(reqByte, CharsetUtil.UTF_8);
        System.out.println("服务端收到请求：" + reqStr);
        String respStr = new StringBuilder(reqStr).append(" 的响应").toString();
        // 回写给客户端和客户端链接中断即短连接，当信息返回给客户端后中断
        ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));
    }
}
