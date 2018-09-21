package com.pdy.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pengdeyao on 2018/9/21
 */

public class converter implements Converter<String,Date> {


    @Override
    public Date convert(String s) {
        try {
            System.out.println("########convert data "+s);
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
