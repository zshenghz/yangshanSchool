package com.zs.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author zhousheng
 * @date 2020年 07月27日 22:47:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.zs"}) //指定扫描位置
@MapperScan("com.zs.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}