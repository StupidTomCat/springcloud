package com.yyh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//eureka和ribbon整合 此处说得不够确切
@SpringBootApplication
@EnableEurekaClient//eureka客户端（应该是消费方的意思）
@EnableFeignClients(basePackages = {"com.yyh.springcloud"})//feign配置扫描包
public class FeignDeptConsumer_8002 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer_8002.class);
    }
}
