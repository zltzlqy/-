package com.ly.serviceDriverUser.controller;

import com.ly.internalcommon.constant.DriverCarConstants;
import com.ly.internalcommon.dto.DriverCarBindingRelationship;
import com.ly.internalcommon.dto.DriverUser;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.DriverUserExistsResponse;
import com.ly.serviceDriverUser.service.DriverCarBindingRelationshipService;
import com.ly.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 新增司机
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 查询 司机
     * 如果需要按照司机的多个条件做查询，那么此处用 /user
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();

        DriverUserExistsResponse response = new DriverUserExistsResponse();

        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        if (driverUserDb == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverUserDb.getDriverPhone());
            response.setIfExists(ifExists);
        }

        return ResponseResult.success(response);
    }

    /**
     * 根据车辆Id查询订单需要的司机信息
     * @param carId
     * @return
     */
    //@GetMapping("/get-available-driver/{carId}")
    //public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
    //    return driverUserService.getAvailableDriver(carId);
    //}

    @Autowired
    DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 根据司机手机号查询司机和车辆绑定关系
     * @param driverPhone
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone){
        return driverCarBindingRelationshipService.getDriverCarRelationShipByDriverPhone(driverPhone);
    }

}