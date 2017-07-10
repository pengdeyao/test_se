package com.pdy.api;

import java.lang.reflect.Proxy;

public class Client {


    public static IUserApi getUserApi() {

        UserApiImpl userApi = new UserApiImpl();

        IUserApi newProxyInstance = (IUserApi) Proxy.newProxyInstance(IUserApi.class.getClassLoader(),
                new Class[] { IUserApi.class },
                new ApiInvocationHandler(userApi));
        return newProxyInstance;
    }

    public static IUserApi getGroupApi() {

        UserApiImpl userApi = new UserApiImpl();

        IUserApi newProxyInstance = (IUserApi) Proxy.newProxyInstance(IUserApi.class.getClassLoader(),
                new Class[] { IUserApi.class }, new ApiInvocationHandler(userApi));
        return newProxyInstance;
    }

    public static void main(String[] args) {

        System.out.println(Client.getUserApi().getUser());
        ;
    }
}

