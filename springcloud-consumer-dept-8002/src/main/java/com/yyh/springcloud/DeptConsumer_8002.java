package com.yyh.springcloud;

import com.yyh.myrule.YyhRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//eureka和ribbon整合 此处说得不够确切
@SpringBootApplication
@EnableEurekaClient//eureka客户端（应该是消费方的意思）
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = YyhRule.class)//提供方的id  加载自定义的ribbon类（自定义负载均衡算法）
public class DeptConsumer_8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_8002.class);
    }
}
