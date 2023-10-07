package com.ly.internalcommon.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 13:16
 * @Description: com.ly.apipassenger.request
 * @version: 1.0
 */
@Data
public class VerificationCodeDTO {


    private String passengerPhone;
    private String verificationCode;
    private String driverPhone;

}
