package com.ly.internalcommon.util;

import com.sun.javafx.tk.TKPulseListener;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/25 - 09 - 25 - 11:24
 * @Description: com.ly.internalcommon.util
 * @version: 1.0
 */


public class RedisPrefixUtils {

    // 乘客验证码的前缀
    public static String verificationCodePrefix = "verification-code-";

    // token存储的前缀
    public static String tokenPrefix = "token-";

    // 黑名单设备号
    public static String blackDeviceCodePrefix = "black-device-";

    /**
     * 根据手机号，生成key
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorKeyByPhone(String phone, String identity){
        return verificationCodePrefix + identity+ "-" + phone;
    }

    /**
     * 根据手机号和身份标识，生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone , String identity , String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
