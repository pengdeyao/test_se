package com.pdy.controller;

import com.pdy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengdeyao on 2018/9/21
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")

    public String test(@ModelAttribute User user){
        System.out.println(user != null ? user.toString() : "");
        return "";
    }

}



