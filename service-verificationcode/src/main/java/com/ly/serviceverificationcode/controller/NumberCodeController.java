package com.ly.serviceverificationcode.controller;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.internalcommon.responese.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/21 - 09 - 21 - 14:39
 * @Description: com.ly.serviceverificationcode.controller
 * @version: 1.0
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        System.out.println("size:"+size);

        double mathRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));
        //System.out.println(mathRandom);
        int resultInt = (int)mathRandom;
        System.out.println("生成随机数:"+resultInt);


        //data.put("numberCode",resultInt);
        NumberCodeResponse response=new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.success(response) ;
    }


}
