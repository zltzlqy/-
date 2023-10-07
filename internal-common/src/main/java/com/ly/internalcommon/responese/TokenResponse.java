package com.ly.internalcommon.responese;

import lombok.Data;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/22 - 09 - 22 - 22:46
 * @Description: com.ly.internalcommon.responese
 * @version: 1.0
 */
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
