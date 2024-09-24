package cn.javabook.chapter09.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 创建Registry，并将其实例化后绑定到指定端口
 *
 */
public class RMIServer {
    public static void main(String[] args) throws Exception {
        RemoteMethod remoteMethod = new RemoteMethodImpl();
        Registry registry = LocateRegistry.createRegistry(9527);
        // 方式一：Naming.bind()
        // Naming.bind("remote", remoteMethod);
        // 方式二：registry.bind()
        registry.bind("remote", remoteMethod);
    }
}
