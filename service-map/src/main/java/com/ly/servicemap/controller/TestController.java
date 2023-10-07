package com.ly.servicemap.controller;

import com.ly.internalcommon.dto.DicDistrict;
import com.ly.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/27 - 09 - 27 - 9:50
 * @Description: com.ly.servicemap.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "map test";
    }

    @Autowired
    DicDistrictMapper dicDistrictMapper;
    
    @GetMapping("/test-map")
    public String testMap(){

        Map<String,Object> map=new HashMap<>();
        map.put("address_code","110000");
        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        System.out.println(dicDistricts);
        return "test_map";

    }
}
