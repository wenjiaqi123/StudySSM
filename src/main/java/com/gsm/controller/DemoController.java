package com.gsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/test")
    public String test(){
        System.out.println("LoginController.loginUser");
        String str = "sss闻家奇";
        return str;
    }

    @RequestMapping("/aaa")
    public Map a(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("msg","闻家奇");
        return map;
    }
}