package cn.javabook.chapter09.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * Netty客户端
 *
 */
public class NettyClient {
    // 要请求的服务器的ip地址
    private final String ip;
    // 服务器的端口
    private final int port;

    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private void start() throws InterruptedException {
        // 连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(bossGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        // 处理来自服务端的响应信息
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                 });
        // 客户端开启
        ChannelFuture future = bootstrap.connect(ip, port).sync();
        // 发送客户端的请求
        future.channel().writeAndFlush(Unpooled.copiedBuffer("客户端请求1".getBytes(CharsetUtil.UTF_8)));
        Thread.sleep(300);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("客户端请求2".getBytes(CharsetUtil.UTF_8)));
        Thread.sleep(300);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("客户端请求3".getBytes(CharsetUtil.UTF_8)));
        // 等待直到连接中断
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient("127.0.0.1", 9527).start();
    }
}
