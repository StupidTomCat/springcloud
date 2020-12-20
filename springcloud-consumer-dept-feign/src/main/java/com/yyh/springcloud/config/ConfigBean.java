package com.yyh.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //相当于spring中的applicationContext.xml
public class ConfigBean {

    //ribbon配置负载均衡实现RestTemplate  就是@LoadBalanced实现的
    //RestTemplate的功能好像是远程访问的实现类（即restful）
    //IRule一个负载均衡接口
    //AvailabilityFilteringRule是IRule的实现类： 会先过滤掉跳闸（崩溃）、访问故障（访问很慢）的服务（提供方），对剩下的进行轮询
    //RoundRobinRule也是IRule的实现类：轮询（默认的）
    //RandomRule也是IRule的实现类：随机
    //RetryRule也是IRule的实现类：先轮询，如果获取服务失败，则会在指定的时间内重试
    @Bean
    @LoadBalanced//开启负载均衡 默认轮询
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
