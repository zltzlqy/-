package com.ly.servicemap.controller;

import com.ly.internalcommon.dto.ResponseResult;
import com.ly.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/28 - 09 - 28 - 15:15
 * @Description: com.ly.servicemap.controller
 * @version: 1.0
 */
@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDistrict(String keywords){

        dicDistrictService.initDicDistrict(keywords);
        return ResponseResult.success();
    }
}
