package com.ly.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 14:17
 * @Description: com.ly.serviceverificationcode.controller
 * @version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "server ver";
    }
}
