package com.ly.apiDriver.controller;

import com.ly.apiDriver.service.UserService;
import com.ly.internalcommon.dto.DriverUser;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.dto.TokenResult;
import com.ly.internalcommon.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        return userService.updateUser(driverUser);
    }
    //
    //@PostMapping("/driver-user-work-status")
    //public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
    //
    //    return userService.changeWorkStatus(driverUserWorkStatus);
    //}

    /**
     * 根据司机token查询 司机和车辆绑定关系
     * @param request
     * @return
     */
//    @GetMapping("/driver-car-binding-relationship")
//    public ResponseResult getDriverCarBindingRelationship(HttpServletRequest request){
//
//        String authorization = request.getHeader("Authorization");
//        TokenResult tokenResult = JwtUtils.checkToken(authorization);
//        String driverPhone = tokenResult.getPhone();
//
//        return userService.getDriverCarBindingRelationship(driverPhone);
//
//    }
//
//    @GetMapping("/work-status")
//    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId){
//        return userService.getWorkStatus(driverId);
//    }
//
}