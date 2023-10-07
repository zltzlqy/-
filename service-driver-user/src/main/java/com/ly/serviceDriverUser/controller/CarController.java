package com.ly.serviceDriverUser.controller;


import com.ly.internalcommon.dto.Car;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.serviceDriverUser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘宇
 * @since 2023-10-03
 */


@RestController
public class CarController {

        @Autowired
        CarService carService;

        @PostMapping("/car")
        public ResponseResult addCar(@RequestBody Car car){
            return carService.addCar(car);
        }

        @GetMapping("/car")
        public ResponseResult<Car> getCarById(Long carId){

            return carService.getCarById(carId);
        }
}


