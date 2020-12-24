package com.yyh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //服务端的启动类  启动注册中心
public class ConfigEurekaServer_7001 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaServer_7001.class,args);
    }
}
