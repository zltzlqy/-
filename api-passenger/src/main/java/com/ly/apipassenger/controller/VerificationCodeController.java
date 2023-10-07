package com.ly.apipassenger.controller;

import com.ly.apipassenger.service.VerificationCodeService;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 13:13
 * @Description: com.ly.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerphone=verificationCodeDTO.getPassengerPhone();
        //System.out.println("后台收到手机号"+passengerphone);
        return verificationCodeService.generatorCode(passengerphone);
    }
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone=verificationCodeDTO.getPassengerPhone();
        String verificationCode=verificationCodeDTO.getVerificationCode();
        //System.out.println("手机号："+passengerPhone+",验证码"+verificationCode);
        return verificationCodeService.checkCode(passengerPhone,verificationCode);

    }
}
