package com.mzyao.ytool.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {


    @GetMapping("ehcache")
    @Cacheable(cacheNames = "oneMinute")
    public String ehcacheReq(){
        return "Hello,ehcache";
    }

}
