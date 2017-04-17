package com.pdy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTrace {

    private static List<Long> timeList = new ArrayList<Long>();

    public static void trace() {
        long currentTimeMillis = System.currentTimeMillis();
        timeList.add(currentTimeMillis);
    }


    public static String getString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TimeTrace []");
        DateFormat dformat = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS");
        long pref = 0 ;
        for(Long time : timeList){
            if(pref == 0){
                builder.append("开始时间"+dformat.format(new Date(time)));
                builder.append(System.getProperty("line.separator"));
            } else {
                builder.append("使用时间" + (time - pref) + "ms");
            }
            pref = time;
        }
        return builder.toString();
    }

    public static void print() {
        System.out.println(getString());
    }


}
