package com.yyh.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class YyhRandomRule extends AbstractLoadBalancerRule {

    //每个服务（提供方）访问5次  然后换下一个服务（3个  8001、8003、8004）
    //total默认等于0，如果等于5，就指向下一个节点
    //currentIndex  如果total ==5，currentIndex++
    private int total = 0;//被调用的次数
    private int currentIndex = 0;//当前是哪一个服务提供方

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//获得活着的服务
                List<Server> allList = lb.getAllServers();//获得全部服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);//生成随机数
//                server = (Server)upList.get(index);//从活着的服务中随机获取一个
                if(total < 5){
                    server = upList.get(currentIndex);//获取指定的服务提供方
                    total++;
                }else {
                    total = 0;
                    currentIndex++;
                    if(currentIndex > upList.size()){
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
