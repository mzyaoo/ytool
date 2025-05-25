package com.mzyao.ytool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {

    @GetMapping("generate/code")
    public String generateCode(){
        return "/html/generate_code";
    }

    @GetMapping("index")
    public String layout(){
        return "/html/layout/index";
    }

}
