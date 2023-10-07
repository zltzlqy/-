package com.ly.apiBoss.service;

import com.ly.apiBoss.remote.ServiceDriverUserClient;
import com.ly.internalcommon.dto.Car;
import com.ly.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }
}
