package com.shen.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shen.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_ok(Integer id) {

        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_ok,Id: "+id+"\t"+"😄";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeOut(Integer id) {

        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        }catch (InterruptedException e){e.printStackTrace();}
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_ok,Id: "+id+"\t"+"耗时（秒）";
    }

    //同来降级的方法
    @Override
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池: "+Thread.currentThread().getName()+" 8001系统繁忙，请稍后再试！！"+id;
    }


    //======服务熔断
    @Override
    @HystrixCommand(fallbackMethod="paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //失败率到达峰值后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id<0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号：" + serialNumber;
    }

    @Override
    public String paymentCircuitBreaker_fallback(Integer id){

        return "id 不能为负数，请稍后再试。。。。   id："+id;
    }


}
