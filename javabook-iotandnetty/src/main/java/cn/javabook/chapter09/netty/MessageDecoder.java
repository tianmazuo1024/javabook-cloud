package cn.javabook.chapter09.netty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * 解码器，客户端和服务端均有使用
 *
 */
public class MessageDecoder extends MessageToMessageDecoder<String> {
    @Override
    protected void decode(ChannelHandlerContext context, String message, List<Object> list) {
        if (message.startsWith("{")) {
            RequestMessage requestMessage = new RequestMessage();
            String content = message.substring(1, message.length() - 1);
            String[] array = content.split("#");
            if (array.length >= 7) {
				requestMessage.setTerm(array[0]);
				requestMessage.setVersion(array[1]);
				requestMessage.setEncry(array[2]);
                requestMessage.setImei(array[3]);
                requestMessage.setCid(array[4]);
				requestMessage.setCmd(array[5]);
				requestMessage.setTime(array[6]);
            }
            if (array.length >= 8) {
                String argsStr = array[7];
                String[] argsArray = argsStr.split(",");
                List<String> args = new ArrayList<String>();
//                for (int i = 0; i < argsArray.length; i++) {
//                    args.add(argsArray[i]);
//                }
                Collections.addAll(args, argsArray);
				requestMessage.setArgs(args);
            }
			list.add(requestMessage);
        }
    }
}
