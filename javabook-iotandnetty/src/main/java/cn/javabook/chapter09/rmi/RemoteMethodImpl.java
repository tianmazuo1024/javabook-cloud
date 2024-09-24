package cn.javabook.chapter09.rmi;

import java.rmi.server.UnicastRemoteObject;

/**
 * 实现了RemoteMethod接口的类
 *
 */
public class RemoteMethodImpl extends UnicastRemoteObject implements RemoteMethod {
    public RemoteMethodImpl() throws Exception{
    }

    @Override
    public String hello(String words) throws Exception {
        System.out.println("服务端：" + words);
        return words;
    }
}
