package com.ly.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 15:14
 * @Description: com.ly.internalcommon.constant
 * @version: 1.0
 */

public enum CommonStatusEnum {
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    SUCCESS(1,"success"),
    FAIL(0,"fail"),

    TOKEN_ERROR(1199,"token错误"),

    //用户不存在
    USER_NOT_EXISTS(1200,"用户不存在"),

    //计价规则不存在1300-1399
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    //地图错误
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),


    /**
     * 司机和车辆：1500-1599
     */
    DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),

    DRIVER_NOT_EXITST(1501,"司机不存在"),

    DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆绑定关系已存在，请勿重复绑定"),

    DRIVER_BIND_EXISTS(1503,"司机已经被绑定了，请勿重复绑定"),

    CAR_BIND_EXISTS(1504,"车辆已经被绑定了，请勿重复绑定"),

    CITY_DRIVER_EMPTY(1505,"当前城市没有可用的司机"),

    AVAILABLE_DRIVER_EMPTY(1506,"可用的司机为空"),
    ;

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
