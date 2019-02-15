package com.pdy.service.impl;

import com.pdy.context.ApplicationContextUtil;
import com.pdy.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by pengdeyao on 2019/1/18
 */
@Service
public class MyServiceImpl implements MyService {

    @PostConstruct
    public void init(){
        System.out.println(getClass().getName()+" init");


    }
}
