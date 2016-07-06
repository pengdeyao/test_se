package com.pdy.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyHelloClient {

    public static void main(String[] args) {
        try {
            // 在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法
            IMyHello rhello = (IMyHello) Naming.lookup("rmi://localhost:8888/RHello");
            System.out.println("客户端收到 " + rhello.sayHello());
            ;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
