package cn.javabook.chapter09.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 数据采集服务端
 *
 */
public class CollectorServer {
	private final ServerBootstrap bootstrap;
	private final EventLoopGroup workerGroup;

	/**
	 * 在构造方法中配置NIO线程组
	 * NioEventLoopGroup包含了一组NIO线程，专门用于网络事件的处理，实际上它们就是Reactor线程组
	 */
	public CollectorServer() {
		// bossGroup用于服务端接受客户端的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// workerGroup则用于负责处理具体工作，实现SocketChannel网络读写
		workerGroup = new NioEventLoopGroup();
		// 启动NIO服务端的辅助启动类
		bootstrap = new ServerBootstrap();

		// 定义Channel的初始化操作
		ChannelInitializer<Channel> initializer = new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel channel) {
				ChannelPipeline pipeline = channel.pipeline();

				// 在pipeline中增加ChannelHandler监听
				pipeline.addLast(new ChannelListener());

				// 在pipeline中增加编码器
				pipeline.addLast(new MessageEncoder());

				// 在pipeline中增加LineBasedFrameDecoder，解决回车换行解码和TCP粘包/拆包
				pipeline.addLast(new LineBasedFrameDecoder(65535));

				// 在pipeline中增加字符串解码器
				pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

				// 在pipeline中增加解码器
				pipeline.addLast(new MessageDecoder());

				// 在pipeline中增加空闲状态超时处理器
				pipeline.addLast(new IdleStateHandler(1, 0, 0, TimeUnit.MINUTES));

				// 在pipeline中增加空闲连接超时处理器
				pipeline.addLast(new IdleConnectionHandler());

				// 在pipeline中增加业务处理器，负责请求及异常处理
				pipeline.addLast("handler", new ServerHandler(() -> {
					try {
						// 优雅关闭
						workerGroup.shutdownGracefully().sync();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}));
			}
		};

		// 将两个NIO线程组当作入参传递到ServerBootstrap
		// 其实就是将NioServerSocketChannel注册到NioEventLoopGroup的Selector中去供后续不断轮询
		bootstrap.group(bossGroup, workerGroup)
				// 设置创建的Channel为NioServerSocketChannel，对应于NIO类库中的ServerSocketChannel类
				.channel(NioServerSocketChannel.class)
				// option主要是设置的ServerChannel的一些选项
				// 配置NioServerSocketChannel的TCP参数，此处将backlog设置为1024
				.option(ChannelOption.SO_BACKLOG, 1024)
				// childOption主要是设置ServerChannel的子Channel的选项
				// 客户端只会有option而没有childOption
				// ByteBuf的分配器，默认值为ByteBufAllocator.DEFAULT
				.childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))
				// 绑定I/O事件的处理类ChildChannelHandler，主要用于处理网络I/O事件，例如记录日志、对消息进行编解码等
				// 这是最重要的一个设置
				.childHandler(initializer);
	}

	/**
	 * 启动服务，监听指定端口
	 */
	private void start() {
		// 监听指定端口
		int port = 9527;
		ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture) throws Exception {
				if (aChannelFuture.isSuccess()) {
					System.out.println("server bound");
				} else {
					System.err.println("bound failure");
					aChannelFuture.cause().printStackTrace();
				}
			}
		});
		try {
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		CollectorServer server = new CollectorServer();
	    server.start();
	}
}
