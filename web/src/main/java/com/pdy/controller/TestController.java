package com.pdy.controller;

import com.pdy.context.ApplicationContextUtil;
import com.pdy.model.User;
import com.pdy.service.MyService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.LastModified;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by pengdeyao on 2018/9/21
 */
@Controller
@RequestMapping("/test")
public class TestController  {

    private long ca = System.currentTimeMillis();

    @Resource
    private MyService myService;

    @RequestMapping("/test")
    @ResponseBody
    public ResponseEntity<String> test(@ModelAttribute User user, WebRequest webRequest, HttpServletResponse response){
        System.out.println(user != null ? user.toString() : "");

        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true);
        customDateEditor.setAsText("2018-11-19");
        System.out.println(customDateEditor.getValue());
        System.out.println(customDateEditor.getAsText());




        if(webRequest.checkNotModified(ca)){
            return null;
        }


        return ResponseEntity.ok("ok");
//        设置页面缓存
//        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1,TimeUnit.MINUTES)).eTag("1").body("ok");

    }

    @RequestMapping("/index")
    @ResponseBody
    public ResponseEntity<String> index(){

       Object obj =  ApplicationContextUtil.getContext().getBean("myFactoryBean");
        System.out.println("###### my factory bean "+obj);
        System.out.println("do service"+ myService.doSomething());
        for(String beadefinitionName : ApplicationContextUtil.getContext().getBeanDefinitionNames()){
            System.out.println(beadefinitionName);
        }
       return ResponseEntity.ok("ok");
    }


    private void testObjF(MyService myService){

    }
}



