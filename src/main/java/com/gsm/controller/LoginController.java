package com.gsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/loginUser")
    public String loginUser(){
        System.out.println("LoginController.loginUser");
        return "hello家奇";
    }
}