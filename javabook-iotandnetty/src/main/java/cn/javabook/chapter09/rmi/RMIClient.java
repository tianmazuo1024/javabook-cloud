package cn.javabook.chapter09.rmi;

import java.rmi.Naming;

/**
 * 远程调用的客户端
 *
 */
public class RMIClient {
    public static void main(String[] args) throws Exception {
        // 方式一：registry.lookup()
        // Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9527);
        // RemoteMethod remote = (RemoteMethod) registry.lookup("remote");
        // System.out.println("Client: " + remote.hello("hello, I'm Client."));
        // 方式二：Naming.lookup()
        RemoteMethod remote = (RemoteMethod) Naming.lookup("rmi://127.0.0.1:9527/remote");
        System.out.println("客户端：" + remote.hello("hello, I'm Client."));
    }
}
