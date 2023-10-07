package com.ly.servicemap.service;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.DirectionResponse;
import com.ly.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

         //调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);


        return ResponseResult.success(direction);
    }
}