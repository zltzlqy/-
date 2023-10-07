package com.ly.apipassenger.controller;

import com.ly.apipassenger.service.TokenService;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/25 - 09 - 25 - 20:09
 * @Description: com.ly.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){


        String refreshTokenSrc=tokenResponse.getRefreshToken();
        System.out.println("原来的"+refreshTokenSrc);
        return tokenService.refreshToken(refreshTokenSrc);
    }
}
