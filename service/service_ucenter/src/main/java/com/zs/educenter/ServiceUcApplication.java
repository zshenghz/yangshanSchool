package com.zs.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author zhousheng
 * @date 2020年 08月01日 16:40:35
 */
@EnableDiscoveryClient
@ComponentScan({"com.zs"})
@SpringBootApplication
@MapperScan("com.zs.educenter.mapper")
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}