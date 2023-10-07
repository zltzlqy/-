package com.ly.apipassenger.service;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ly.internalcommon.constant.CommonStatusEnum;
import com.ly.internalcommon.constant.TokenConstants;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.dto.TokenResult;
import com.ly.internalcommon.responese.TokenResponse;
import com.ly.internalcommon.util.JwtUtils;
import com.ly.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/25 - 09 - 25 - 20:13
 * @Description: com.ly.apipassenger.service
 * @version: 1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析判断refreshtoken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);

        if(tokenResult==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
            //拼接
            String phone=tokenResult.getPhone();
            String identity=tokenResult.getIdentity();

            String refreshtokenKey= RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.REFRESH_TOKEN_TYPE);
            String refreshtokenRedis = stringRedisTemplate.opsForValue().get(refreshtokenKey);

            if(StringUtils.isBlank(refreshtokenRedis)){
                return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
            }else {
                if(!refreshTokenSrc.trim().equals(refreshtokenRedis.trim())){
                    return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
                }
            }


        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

        String accesstokenKey= RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accesstokenKey,accessToken,30,TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshtokenKey,refreshToken,31, TimeUnit.DAYS);

        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return ResponseResult.success(tokenResponse);
    }
}
