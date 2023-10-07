package com.ly.apipassenger.remote;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 20:21
 * @Description: com.ly.apipassenger.remote
 * @version: 1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationClient {

    @RequestMapping(method = RequestMethod.GET,value="/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);

}
