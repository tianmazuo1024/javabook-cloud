package cn.javabook.chapter09.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务端
 *
 */
public class NettyServer {
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    // 服务端启动
    public void start() throws InterruptedException {
        // 负责处理连接请求的线程（主线程）
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责处理具体工作的线程（从线程）
        // 一旦bossGroup接收到连接，就会把连接信息注册到workerGroup上
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 服务启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 配置服务参数
            // 主从线程
            bootstrap.group(bossGroup, workerGroup)
                     // 接收新连接
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel channel) {
                             // 处理接收到的请求，可以配置多种不同类别的和用途的ChannelHandler
                             channel.pipeline().addLast(new ServerHandler());
                         }
                     });
            // 绑定端口，开始接受链接
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("服务端开启...");
            // 等待服务端口的关闭
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 开启netty服务
    public static void main(String[] args) throws InterruptedException {
        new NettyServer(9527).start();
    }
}
