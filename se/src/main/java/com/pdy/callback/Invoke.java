package com.pdy.callback;

public class Invoke {

    private ICallback callbackble;
    
    public Invoke(ICallback callbackble) {
        super();
        this.callbackble = callbackble;
    }

    public void  invoke(){
        System.out.println("dosomething");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callbackble.callback();
    }

    
    
    
}
