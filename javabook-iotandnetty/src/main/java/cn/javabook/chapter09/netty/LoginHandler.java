package cn.javabook.chapter09.netty;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录处理器
 *
 */
public class LoginHandler {
    private static final LoginHandler instance = new LoginHandler();

    public static LoginHandler getInstance() {
        return instance;
    }

    public ResponseMessage login(ChannelInfo channelInfo, RequestMessage request) {
        TerminalManager terminalManager = TerminalManager.getInstance();
        String imei = request.getImei();
        ResponseMessage message = new ResponseMessage(request);
        message.setCmd("S1");
        // 当前终端是否已登录
        if (terminalManager.isLogin(channelInfo)) {
            // 防止同一个终端用多个imei登录
            System.out.println("终端 " + channelInfo.getImei() + " 已登录");
        } else {
            System.out.println("终端 " + imei + " 第一次登录");
            terminalManager.login(imei, channelInfo);
        }
        List<String> args = new ArrayList<String>();
        args.add("1");
        args.add(String.valueOf(System.currentTimeMillis()));
        message.setArgs(args);
        return message;
    }
}
