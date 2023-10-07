package com.ly.servicepassengeruser.controller;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import com.ly.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/24 - 09 - 24 - 10:47
 * @Description: com.ly.servicepassengeruser.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号"+passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){

        System.out.println("1111111");
        return userService.getUserByPhone(passengerPhone);
    }

}
