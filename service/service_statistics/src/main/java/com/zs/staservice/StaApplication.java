package com.zs.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhousheng
 * @date 2020年 08月09日 13:04:40
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zs"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zs.staservice.mapper")
@EnableScheduling
public class StaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
