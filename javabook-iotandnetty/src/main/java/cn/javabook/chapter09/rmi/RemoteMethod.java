package cn.javabook.chapter09.rmi;

import java.rmi.Remote;

/**
 * 继承Remote远程接口
 *
 */
public interface RemoteMethod extends Remote {
    public String hello(String words) throws Exception;
}
