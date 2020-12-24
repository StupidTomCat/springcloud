package com.yyh.springcloud.controller;

import com.yyh.springcloud.pojo.Dept;
import com.yyh.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供restful服务
@RestController//以json格式
public class DeptController {

    @Autowired
    private DeptService deptService;
    //获取微服务信息
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //本8001注册的微服务 获取一些消息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表清单
        List<String> services = client.getServices();
        System.out.println("Discover=>services"+services);

        //得到一个具体微服务信息  通过微服务的id(页面中表头Application位置)
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                    instance.getPort()+"\t"+
                    instance.getUri()+"\t"+
                    instance.getServiceId()+"\t"
            );
        }
        return this.client;
    }

}
