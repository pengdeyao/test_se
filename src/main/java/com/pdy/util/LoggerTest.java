package com.pdy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pengdeyao on 2017/7/9.
 */
public class LoggerTest {
    private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    public static void main(String[] args) {


        int count= 100000;
        String content = "我是好人啊啊是范德萨";
        TimeTrace.trace();
        write(count,content);
        TimeTrace.trace();
        TimeTrace.print();
    }


    public static void write(int count,String content){
        for(int i=0;i<count;i++){
            logger.info(content);
        }
    }
}
