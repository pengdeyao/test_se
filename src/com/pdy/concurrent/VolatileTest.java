package com.pdy.concurrent;

/**
 * <PRE>
 * volatile 关键词的作用:<BR>
 * 1.一个线程修改的值，对另一个线程立即可见。<BR>
 * 2.导致读入cpu缓存的值失效，每次都从主内存读取。<BR>
 * 3.解决指令重排的问题(指令重排: 一个线程对变量a,b进行操作，且a,b直接不互相依赖，b的值可能先于a的值返还。如: a = 1 ,b=2)<BR>
 * 
 * ==============================<BR>
 * 由测试结果可知，volatile的变量读取速度比非volatile的变量低8倍左右.<BR>
 * 读写的区别几千倍.<BR>
 * 所以在单例模式中不建议用双重检查加锁的方式，应该采用静态内部类的方式。
 * 
 * @author pengdeyao
 *
 */
public class VolatileTest {

    public static volatile int a = 0;

    public static int b = 0;

    public static void main(String[] args) {
        testRead();
        testRead2();
        testReadWrite();
        testReadWrite2();
    }

    public static void testRead() {
        long mSeconds = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            int c = a;
        }
        System.out.println("测试读" + (System.currentTimeMillis() - mSeconds));
    }

    public static void testRead2() {
        long mSeconds = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            int c = b;
        }
        System.out.println("测试读" + (System.currentTimeMillis() - mSeconds));
    }

    public static void testReadWrite() {
        long mSeconds = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            a = a++;
        }
        System.out.println("测试读" + (System.currentTimeMillis() - mSeconds));
    }

    public static void testReadWrite2() {
        long mSeconds = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            b = b++;
        }
        System.out.println("测试读" + (System.currentTimeMillis() - mSeconds));
    }
}
