package com.ly.serviceDriverUser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGenerator {
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root","root")
                .globalConfig(builder -> {
                    builder.author("刘宇").fileOverride().outputDir("D:\\ZZZZZZZStudy\\javalianxi\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    //父包
                    builder.parent("com.ly.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            //mapper路径
                            "D:\\ZZZZZZZStudy\\javalianxi\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\ly\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    //加前后缀
                    builder.addInclude("driver_user_work_status");

                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}