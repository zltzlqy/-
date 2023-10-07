package com.ly.apipassenger.controller;

import com.ly.apipassenger.service.UserService;
import com.ly.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/26 - 09 - 26 - 15:22
 * @Description: com.ly.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        String accessToken =request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessToken);
    }
}
