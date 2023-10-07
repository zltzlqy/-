package com.ly.apipassenger.service;

import com.alibaba.nacos.common.model.RestResult;
import com.ly.apipassenger.remote.ServicePassengerUserClient;
import com.ly.internalcommon.dto.PassengerUser;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.dto.TokenResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import com.ly.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/26 - 09 - 26 - 15:25
 * @Description: com.ly.apipassenger.service
 * @version: 1.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken:"+accessToken);
        //解析accessToken
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone=tokenResult.getPhone();
        log.info("手机号+"+phone);


        //根据手机号查

        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);


        return ResponseResult.success(userByPhone.getData());
    }
}
