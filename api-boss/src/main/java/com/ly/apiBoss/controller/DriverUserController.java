package com.ly.apiBoss.controller;

import com.ly.apiBoss.service.CarService;
import com.ly.apiBoss.service.DriverUserService;
import com.ly.internalcommon.dto.Car;
import com.ly.internalcommon.dto.DriverUser;
import com.ly.internalcommon.dto.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 添加司机
     * @param driverUser
     * @return
     */
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        System.out.println((JSONObject.fromObject(driverUser).toString()));;
        return driverUserService.addDriverUser(driverUser);

    }

    /**
     * 修改司机
     * @param driverUser
     * @return
     */
    //@PutMapping("/driver-user")
    //public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
    //    return driverUserService.updateDriverUser(driverUser);
    //}
    //
    //
    @Autowired
    CarService carService;

    @PostMapping("/car")
    public ResponseResult car(@RequestBody Car car){

        return carService.addCar(car);
    }
}