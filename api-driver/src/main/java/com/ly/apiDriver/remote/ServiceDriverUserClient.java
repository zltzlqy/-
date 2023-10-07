package com.ly.apiDriver.remote;

import com.ly.internalcommon.dto.Car;
import com.ly.internalcommon.dto.DriverCarBindingRelationship;
import com.ly.internalcommon.dto.DriverUser;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.GET, value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);

    @RequestMapping(method = RequestMethod.GET, value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);

    //@RequestMapping(method = RequestMethod.POST, value="/driver-user-work-status")
    //public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus);

    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone);

    //@GetMapping("/work-status")
    //public ResponseResult<DriverUserWorkStatus> getWorkStatus(@RequestParam Long driverId);
}