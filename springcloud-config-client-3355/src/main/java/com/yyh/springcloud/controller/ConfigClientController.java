package com.yyh.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    //下面三个属性都是从github上面的config-client.yml文件拿到的
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig(){
        return "从github读取的配置 applicationName:" +applicationName
                +"  eurekaServer:" +eurekaServer
                +"  port:"+port;
    }
}
