package com.ly.apipassenger.service;

import com.ly.apipassenger.remote.ServicePassengerUserClient;
import com.ly.apipassenger.remote.ServiceVerificationClient;
import com.ly.internalcommon.constant.CommonStatusEnum;
import com.ly.internalcommon.constant.IdentityConstant;
import com.ly.internalcommon.constant.IdentityConstants;
import com.ly.internalcommon.constant.TokenConstants;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import com.ly.internalcommon.responese.NumberCodeResponse;
import com.ly.internalcommon.responese.TokenResponse;
import com.ly.internalcommon.util.JwtUtils;
import com.ly.internalcommon.util.RedisPrefixUtils;
import com.sun.javafx.geom.transform.Identity;
import io.lettuce.core.codec.ToByteBufEncoder;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 13:21
 * @Description: com.ly.apipassenger.service
 * @version: 1.0
 */

@Service
public class VerificationCodeService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private ServiceVerificationClient serviceVerificationClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone){

        //调用验证码服务
        System.out.println("调用验证码服务");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode=numberCodeResponse.getData().getNumberCode();

        //存入redis
        System.out.println("调用生成的是:"+numberCode);

        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        //key value ddl
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);
        System.out.println("存入redis的key是： "+numberCode);
        System.out.println("存入redis的value是： "+numberCode);

        //短信调用
        return ResponseResult.success("");
    }




    public ResponseResult checkCode(String passengerPhone,String verificationCode){
       //根据手机号读取验证码
        System.out.println("根据手机号读取验证码");

        //生成key
        String key =RedisPrefixUtils.generatorKeyByPhone(passengerPhone,IdentityConstants.PASSENGER_IDENTITY);
        //根据KEY获取val
        //redis-server redis.windows.conf
        //redis-server --service-uninstall
        String codeRedis=stringRedisTemplate.opsForValue().get(key);
        System.out.println("调用检查的是"+verificationCode);
        System.out.println("从Redis中取出为"+codeRedis);


        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //System.out.println("判断是否由用户");
        VerificationCodeDTO verificationCodeDTO=new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        //System.out.println("颁发令牌");\
        String accessToken= JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken=JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);



        //将token存入redis
        String accesstokenKey=RedisPrefixUtils.generatorTokenKey(passengerPhone,IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accesstokenKey,accessToken,30,TimeUnit.DAYS);

        String refreshtokenKey=RedisPrefixUtils.generatorTokenKey(passengerPhone,IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshtokenKey,refreshToken,31,TimeUnit.DAYS);



        //响应
        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
