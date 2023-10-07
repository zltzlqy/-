package com.ly.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/23 - 09 - 23 - 23:37
 * @Description: com.ly.servicepassengeruser
 * @version: 1.0
 */
//redis-server redis.windows.conf
//redis-server --service-uninstall
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ly.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
