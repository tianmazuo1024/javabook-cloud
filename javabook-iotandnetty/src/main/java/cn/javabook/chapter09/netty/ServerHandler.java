package cn.javabook.chapter09.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 业务处理器
 *
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {
    private final Runnable shutdownAction;

    public ServerHandler(Runnable shutdownAction) {
        this.shutdownAction = shutdownAction;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println(cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 收到消息后的处理
     *
     * @param ctx
     * @param object
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object object) throws InterruptedException {
        TerminalManager terminalManager = TerminalManager.getInstance();
        // 将object转换为RequestMessage对象
        RequestMessage message = (RequestMessage) object;
        if (message != null) {
            String imei = message.getImei();
            // 得到指令类型
            String command = message.getCmd();
            System.out.println("收到终端 " + imei + " 发出的 " + command + " 指令");
            // 获取当前终端的ChannelInfo
            ChannelInfo channelInfo = ctx.channel().attr(ChannelListener.CHANNEL_INFO).get();
            ResponseMessage response = null;
            // 处理登录请求
            if (command.equals("T1")) {
                response = LoginHandler.getInstance().login(channelInfo, message);
                // 将终端imei和ChannelHandlerContext保存到进程内或进程外缓存中，后续使用
                CommandDispatcher.cacheMap.put(imei, ctx);
                ctx.writeAndFlush(response);
            } else {
                terminalManager.logout(channelInfo);
                ctx.close();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            // 指令下发：服务端主动给客户端发送指令
            List<String> list = new ArrayList<String>();
            list.add("1");
            list.add(String.valueOf(System.currentTimeMillis()));
            ResponseMessage result = new ResponseMessage("1", "0.0.2", "1", imei, "xyz", "S1", "2024202420242024", list);
            ctx = (ChannelHandlerContext) CommandDispatcher.cacheMap.get(imei);
            ctx.writeAndFlush(result);
        } else {
            ctx.fireChannelRead(null);
        }
    }
}
