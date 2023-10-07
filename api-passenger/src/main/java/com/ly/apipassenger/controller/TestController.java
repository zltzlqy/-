package com.ly.apipassenger.controller;

import com.ly.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 12:53
 * @Description: com.ly.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){

        return "test api passenger";
    }

    //@GetMapping("/verification-code")
    //public String verificationCode(){
    //
    //    return "test api passenger";
    //}

    //要有token
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }
    //不需要tokrn
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("no auth test");
    }


}
