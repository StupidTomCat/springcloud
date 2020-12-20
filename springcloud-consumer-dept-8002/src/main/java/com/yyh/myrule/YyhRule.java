package com.yyh.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//此自定义负载均衡算法类不能被spring（@ComponentScan）扫描到
@Configuration
public class YyhRule {

    //自定义负载均衡算法，将RoundRobinRule改为YyhRandomRule（后者是自定义的，非随机也非轮询）
    @Bean
    public IRule myRule(){
        return new RoundRobinRule();
    }
}
