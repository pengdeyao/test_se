package com.pdy.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ApiInvocationHandler implements InvocationHandler {

    private IUserApi userApi;

    public ApiInvocationHandler(IUserApi userApi) {
        super();
        this.userApi = userApi;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before" + proxy.getClass());
        Object result = null;
        try {
            result = method.invoke(userApi, args);
        } catch (Exception e) {
            result = method.invoke(userApi, args);
        }
        System.out.println("after");
        return result;
    }

}
