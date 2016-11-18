package com.pdy.callback;

public class Callbackble {

    public void invoke() {
        System.out.println("-----");
        new Invoke(new ICallback() {
            @Override
            public void callback() {
                Callbackble.this.callback();
            }
        }).invoke();
    }

    public void callback() {
        System.out.println("回调执行完成");
    }

}
