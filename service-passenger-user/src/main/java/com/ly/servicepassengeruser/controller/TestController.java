package com.ly.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/23 - 09 - 23 - 23:31
 * @Description: com.ly.servicepassengeruser.controller
 * @version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "service-passenger-user";
    }
}
