package com.pdy.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 测试logback日志
 * Created by pengdeyao on 2017/7/3.
 */
public class LogBackTest {
    private static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    public static void main(String[] args) {
        while (true) {
            logger.info("haha" + new Date() + " " + System.currentTimeMillis());
            logger.error("haha" + new Date() + " " + System.currentTimeMillis());
            try {
                throw new Exception("asss");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
