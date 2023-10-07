package com.ly.servicepassengeruser.service;

import com.ly.internalcommon.constant.CommonStatusEnum;
import com.ly.internalcommon.dto.PassengerUser;
import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.request.VerificationCodeDTO;
import com.ly.servicepassengeruser.mapper.PassengerUserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.PseudoColumnUsage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/24 - 09 - 24 - 11:04
 * @Description: com.ly.servicepassengeruser.service
 * @version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    public ResponseResult loginOrRegister(String passengerPhone){

        //根据手机号查询用户
        System.out.println("调用login");

        Map<String,Object> map=new HashMap<>();
        map.put("passenger_phone",passengerPhone);

        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size()==0?"无记录":passengerUsers.get(0).getPassengerPhone());

        if(passengerUsers.size()==0){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            //插入
            passengerUserMapper.insert(passengerUser);
        }


        //判断是否存在passenger_phone
        //弱弱U
        System.out.println("调到UserService  "+passengerPhone);
        return ResponseResult.success();
    }


    public ResponseResult getUserByPhone(String passengerPhone){

        Map<String,Object> map=new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);



        if(passengerUsers.size()==0){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getValue());
        }else{
            PassengerUser passengerUser=passengerUsers.get(0);
            return  ResponseResult.success(passengerUser);
        }

    }

}
