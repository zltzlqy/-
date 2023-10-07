package com.ly.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ly.internalcommon.constant.IdentityConstant;
import com.ly.internalcommon.constant.TokenConstants;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.dto.TokenResult;
import com.ly.internalcommon.util.JwtUtils;
import com.ly.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/25 - 09 - 25 - 9:31
 * @Description: com.ly.apipassenger.interceptor
 * @version: 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result=true;
        String resultString="";

        String token = request.getHeader("Authorization");
        //解析token
        TokenResult tokenResult=JwtUtils.checkToken(token);

        //从redis取出
        //比较

        if(tokenResult==null){
            resultString="token invalid";
            result=false;
        }else {
            //拼接
            String phone=tokenResult.getPhone();
            String identity=tokenResult.getIdentity();

            String tokenKey= RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.ACCESS_TOKEN_TYPE);

            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);

            if(StringUtils.isBlank(tokenRedis)){
                resultString="isBlank";
                result=false;
            }else {
                if(!token.trim().equals(tokenRedis.trim())){
                    resultString=" acc token invalid";
                    result=false;
                }
            }
        }


        if(!result){
            PrintWriter out=response.getWriter();
            out.println(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
