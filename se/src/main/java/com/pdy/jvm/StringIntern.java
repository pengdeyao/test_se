package com.pdy.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 jvm  方法区常量池
 *
 * jdk1.7 之前运行时常量池在 方法区中（永久代)
 * jdk1.7 之后运行时常量池在 堆中
 */
public class StringIntern {

    public static void main(String[] args) {
        System.out.println(System.getProperties());
        String s = new String("BB"); //堆中创建了一个对象,并返回引用，并添加BB到常量池
        String ss = "BB"; // 想在常量池中查找BB，存在返回BB的引用
        String sss = s.intern(); //返回BB的引用
        System.out.println(s == ss);
        System.out.println(ss == sss);
        System.out.println(s == sss);
    }
}
