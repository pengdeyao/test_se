package com.pdy.callback;

public class Callbackble implements ICallback {

    public void invoke() {
        new Invoke(this).invoke();
    }

    @Override
    public void callback() {
        System.out.println("回调执行完成");
    }

}
