package com.pdy.concurrent.lock;

/**
 * 计数器<BR>
 * 保证多线程下的并发安全<BR>
 * 
 * @author pengdeyao
 *
 */
public class Counter {

    protected ILock myLock = null;
    protected int num = 0;

    public Counter(ILock myLoock) {
        super();
        this.myLock = myLoock;
    }

    public void incr() throws InterruptedException {
        myLock.lock();
        num++;
        myLock.unLock();
    }

    public int getNum() {
        return num;
    }
}

class Counter2 extends Counter {

    public Counter2(ILock myLoock) {
        super(myLoock);
    }

    public void incr() throws InterruptedException {
        super.myLock.lock();
        super.num++;
        super.myLock.lock();
        super.myLock.lock();
        super.myLock.unLock();
        super.myLock.unLock();
        super.myLock.unLock();
    }

    public int getNum() {
        return num;
    }

}
