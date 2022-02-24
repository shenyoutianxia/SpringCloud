package com.shen.congfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class webConfig {

    @Bean
    @LoadBalanced    //使用注解@LoadBalanced赋予负载均衡的能力
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
