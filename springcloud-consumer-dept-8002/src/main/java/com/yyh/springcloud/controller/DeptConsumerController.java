package com.yyh.springcloud.controller;

import com.yyh.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    //消费者不应该有service层  所以用restful（应该是基于http）
    //RestTemplate  直接调用  注册到spring中
    //（url，实体，Class<T> responseType）

    @Autowired
    private RestTemplate restTemplate;//提供多种便捷远程访问http服务的方法 简单的restful服务

    //用ribbon的话，这里的地址应该是一个变量  通过服务名来访问
    //private static final String RESR_URL_PREFIX = "http://localhost:8001";
    private static final String RESR_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(RESR_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    //http://localhost:8001/dept/list
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(RESR_URL_PREFIX+"/dept/get/"+id,Dept.class);//http的get请求
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(RESR_URL_PREFIX+"/dept/list",List.class);
    }
}
