package cn.javabook.chapter09.netty;

import java.nio.charset.StandardCharsets;
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器，客户端与服务器都有使用
 *
 */
public class MessageEncoder extends MessageToByteEncoder<ResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext arg0, ResponseMessage message, ByteBuf out) {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append(message.getTerm()).append("#");
        buf.append(message.getVersion()).append("#");
        buf.append(message.getEncry()).append("#");
        buf.append(message.getImei()).append("#");
        buf.append(message.getCid()).append("#");
        buf.append(message.getCmd()).append("#");
        buf.append(System.currentTimeMillis());

        List<String> args = message.getArgs();
        if (args != null) {
            if (!args.isEmpty()) {
                buf.append("#");
            }
            for (int i = 0; i < args.size(); i++) {
                buf.append(args.get(i));
                if (i < args.size() - 1) {
                    buf.append(",");
                }
            }
        }
        // 回车换行为指令结束标志，否则无法判断指令边界
        buf.append("}").append("\r\n");
        out.writeBytes(buf.toString().getBytes(StandardCharsets.UTF_8));
    }
}
