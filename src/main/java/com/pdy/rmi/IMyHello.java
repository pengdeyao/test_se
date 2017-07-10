package com.pdy.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常
 * 
 * @author pengdeyao
 *
 */
public interface IMyHello extends Remote {

    public String sayHello() throws RemoteException;
}
