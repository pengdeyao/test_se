package com.pdy.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyHelloImpl extends UnicastRemoteObject implements IMyHello {


    protected MyHelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        String str = "hello work";
        System.out.println(str);
        return str;
    }

}
