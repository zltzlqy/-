package com.ly.internalcommon.util;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ly.internalcommon.dto.TokenResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import com.sun.javafx.tk.TKClipboard;
import com.sun.prism.shader.Solid_Color_AlphaTest_Loader;
import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.parser.TokenType;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.sql.ClientInfoStatus;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/24 - 09 - 24 - 20:41
 * @Description: com.ly.internalcommon.util
 * @version: 1.0
 */
public class JwtUtils {
    //生成token
    private static final String SIGN = "lyzly!@#$$";
    //解析token

    private static final String JWT_KEY_phone="Phone";
    //乘客1 司机2
    private static final String JWT_KEY_IDENTITY="identity";

    //token类型
    private static final String JWT_TOKEN_TYPE="tokenType";

    private static  final String JWT_TOKEN_TIME="tokenTime";

    public static String generatorToken(String passengerPhone,String identity,String tokenType){

        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY_phone,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);

        //过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date= calendar.getTime();
        map.put(JWT_TOKEN_TIME,date.toString());


        JWTCreator.Builder builder= JWT.create();
        //整合map
        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );
        //整合过期时间
        //builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    //解析token
    public  static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_phone).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult=new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }
    //校验token判断是否异常
    public static TokenResult checkToken(String token){
        TokenResult tokenResult=null;
        try{
            tokenResult =JwtUtils.parseToken(token);
        }catch(Exception e) {

        }
        return tokenResult;
    }

    public static void main(String[] args) {

        String s = generatorToken("17629091762","1","accessToken");
        System.out.println("生成"+s);
        String phone = parseToken(s).getPhone();
        String identity = parseToken(s).getIdentity();
        System.out.println("解析"+ phone+"   "+identity);
    }

}
