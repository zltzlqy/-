package com.ly.apipassenger.remote;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.ForecastPriceDTO;
import com.ly.internalcommon.responese.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/27 - 09 - 27 - 22:55
 * @Description: com.ly.apipassenger.remote
 * @version: 1.0
 */
@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.POST,value="/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
